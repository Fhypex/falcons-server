package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.AmenityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Address;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import gtu.cse.se.altefdirt.aymoose.shared.domain.PhoneNumber;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class CreateFacilityCommandHandler implements CommandHandler<CreateFacility, Facility> {

    private final FacilityFactory factory;
    private final AmenityService amenityService;
    private final FacilityRepository facilityRepository;
    private final DistrictRepository districtRepository;
    private final ImageOperationPort imageOperationPort;

    @Override
    public Facility handle(CreateFacility command) {

        log.debug("Handling create facility command {}", command);
        District district = districtRepository.findById(command.districtId()).get();

        log.debug("Creating facility for district {}", district);

        List<AggregateId> amenities = command.amenities().stream().map(AggregateId::from).toList();
        if (!amenityService.validateAmenities(amenities)) {
            throw new IllegalArgumentException("Invalid amenities");
        }

        Facility facility = factory.create(
                AggregateId.from(command.userId()),
                new PhoneNumber(command.phoneNumber()),
                command.name(),
                command.description(),
                new Address(district.cityId(), command.districtId(), command.fullAddress()),
                new Location(command.location()),
                command.contactDetails(),
                new WorkHours(command.openTime(), command.closeTime()),
                amenities,
                command.isActive());

        log.debug("Saving facility {}", facility);
        
        Facility savedFacility = facilityRepository.save(facility);

        log.debug("Facility saved {}", savedFacility);


        log.debug("District updated {}", district);

        for (MultipartFile image : command.images()) {
            imageOperationPort.save(savedFacility.id(), image);
        }
        
        log.debug("Images saved for facility {}", savedFacility);
        return savedFacility;
    }
}
