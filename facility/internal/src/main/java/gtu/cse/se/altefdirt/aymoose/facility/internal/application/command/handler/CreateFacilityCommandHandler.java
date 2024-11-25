package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.AmenityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.CityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.City;
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

@RegisterHandler
@RequiredArgsConstructor
public class CreateFacilityCommandHandler implements CommandHandler<CreateFacility, FacilityView> {

    private final FacilityFactory factory;
    private final FacilityService service;
    private final AmenityService amenityService;
    private final FacilityRepository facilityRepository;
    private final DistrictRepository districtRepository;

    @Override
    public FacilityView handle(CreateFacility command) {

        District district = districtRepository.findById(command.districtId()).get();

        List<AggregateId> amenities = command.amenities().stream().map(AggregateId::from).toList();
        if(!amenityService.validateAmenities(amenities)) {
            throw new IllegalArgumentException("Invalid amenities");
        }

        Facility facility = factory.create(
                AggregateId.from(command.ownerId()),
                new PhoneNumber(command.phoneNumber()),
                command.name(),
                command.description(),
                new Address(district.cityId(), command.districtId(),command.fullAddress()),
                new Location(command.location()),
                command.contactDetails(),
                new WorkHours(command.openTime(), command.closeTime()),
                amenities,
                command.isActive());

        Facility savedFacility = facilityRepository.save(facility);

        district.setInUse(true);
        districtRepository.save(district);

        return service.denormalize(savedFacility);
    }
}
