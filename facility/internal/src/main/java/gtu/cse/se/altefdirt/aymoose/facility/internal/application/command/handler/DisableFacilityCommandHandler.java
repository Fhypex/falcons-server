package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DisableFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class DisableFacilityCommandHandler implements CommandHandler<DisableFacility, Facility> {

    private final FacilityRepository facilityRepository;

    @Override
    public Facility handle(DisableFacility command) {
        Optional<Facility> fetch = facilityRepository.findById(AggregateId.fromUUID(command.facilityId()));
        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Facility does not exist");
        }
        Facility facility = fetch.get();
        facility.deactivate();
        return facilityRepository.save(facility);
    }
}
