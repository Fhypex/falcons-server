package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.UpdateFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class UpdateFacilityCommandHandler implements CommandHandler<UpdateFacility, FacilityView> {

    private final FacilityService service;
    private final FacilityRepository repository;

    @Override
    public FacilityView handle(UpdateFacility command) {

        Optional<Facility> fetch = repository.findById(AggregateId.from(command.id()));

        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Facility does not exist");
        }

        Facility facility = fetch.get();

        facility.updateFacilityDetails(command.facilityName(), command.facilityDescription());
        facility.updateContactDetails(command.contactDetails(), command.phoneNumber());
        facility.updateLocation(command.latitude(), command.longitude());
        facility.updateCourtCount(command.courtCount());

        Facility savedFacility = repository.save(facility);

        return service.denormalize(savedFacility);
    }
}
