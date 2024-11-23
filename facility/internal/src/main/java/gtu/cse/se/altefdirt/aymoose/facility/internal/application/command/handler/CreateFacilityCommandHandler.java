package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Address;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import gtu.cse.se.altefdirt.aymoose.shared.domain.PhoneNumber;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class CreateFacilityCommandHandler implements CommandHandler<CreateFacility, FacilityView> {

    private final FacilityFactory factory;
    private final FacilityService service;
    private final FacilityRepository facilityRepository;

    @Override
    public FacilityView handle(CreateFacility command) {

        Facility facility = factory.create(
                AggregateId.from(command.ownerId()),
                new PhoneNumber(command.phoneNumber()),
                command.name(),
                command.description(),
                new Address(command.city(), command.district(),command.fullAddress()),
                new Location(command.location()),
                command.contactDetails(),
                command.isActive());

        Facility savedFacility = facilityRepository.save(facility);

        return service.denormalize(savedFacility);
    }
}
