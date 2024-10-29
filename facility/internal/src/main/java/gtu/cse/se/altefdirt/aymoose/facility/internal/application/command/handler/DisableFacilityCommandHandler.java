package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DisableFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class DisableFacilityCommandHandler implements CommandHandler<DisableFacility, FacilityView> {

    private final FacilityService service;
    private final FacilityRepository facilityRepository;

    @Override
    public FacilityView handle(DisableFacility command) {
        Optional<Facility> fetch = facilityRepository.findById(AggregateId.from(command.facilityId()));
        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Facility does not exist");
        }
        Facility facility = fetch.get();
        facility.deactivate();
        return service.denormalize(facilityRepository.save(facility));
    }
}

