package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.UpdateFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.AmenityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class UpdateFacilityCommandHandler implements CommandHandler<UpdateFacility, Facility> {

    private final AmenityService amenityService;
    private final FacilityRepository facilityRepository;
    private final DistrictRepository districtRepository;
    private final ImageOperationPort imageOperationPort;

    @Override
    public Facility handle(UpdateFacility command) {

        Optional<Facility> fetch = facilityRepository.findById(AggregateId.fromUUID(command.id()));
        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Facility does not exist");
        }

        Facility facility = fetch.get();

        if (command.districtId() != null) {
            Optional<District> district = districtRepository.findById(command.districtId());
            if (district.isEmpty()) {
                throw new IllegalArgumentException("District does not exist");
            }
            facility.updateDistrictId(command.districtId());
        }

        if (command.phoneNumber() != null) {
            facility.updatePhoneNumber(command.phoneNumber());
        }

        if (command.name() != null) {
            facility.updateName(command.name());
        }

        if (command.amenities() != null) {
            if (!amenityService.validateAmenities(command.amenities().stream().map(AggregateId::fromUUID).toList())) {
                throw new IllegalArgumentException("Invalid amenities");
            }
            facility.updateAmenities(command.amenities().stream().map(AggregateId::fromUUID).toList());
        }

        if (command.description() != null) {
            facility.updateDescription(command.description());
        }

        if (command.fullAddress() != null) {
            facility.updateFullAddress(command.fullAddress());
        }

        if (command.location() != null) {
            facility.updateLocation(command.location());
        }

        if (command.contactDetails() != null) {
            facility.updateContactDetails(command.contactDetails());
        }

        if (command.openTime() != null) {
            facility.updateOpenTime(command.openTime());
        }

        if (command.closeTime() != null) {
            facility.updateCloseTime(command.closeTime());
        }

        Facility savedFacility = facilityRepository.save(facility);

        if (command.newImages() != null) {
            for (MultipartFile image : command.newImages()) {
                imageOperationPort.save(savedFacility.id(), image);
            }
        }
        if (command.deletedImages() != null) {
            for (UUID image : command.deletedImages()) {
                imageOperationPort.delete(AggregateId.fromUUID(image));
            }
        }

        return savedFacility;
    }
}
