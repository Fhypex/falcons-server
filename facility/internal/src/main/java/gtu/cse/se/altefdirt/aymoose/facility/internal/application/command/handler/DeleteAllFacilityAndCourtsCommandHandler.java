package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import java.util.ArrayList;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteAllFacilityAndCourts;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class DeleteAllFacilityAndCourtsCommandHandler implements CommandHandler<DeleteAllFacilityAndCourts, Integer> {

    private final FacilityRepository facilityRepository;
    private final ImageOperationPort imageOperationPort;
    private final CourtRepository courtRepository;

    @Override
    @Transactional
    public Integer handle(DeleteAllFacilityAndCourts command) {

        List<AggregateId> facilityIds = facilityRepository.findAll().stream().map(f -> f.id()).toList();
        List<AggregateId> courtIds = courtRepository.findAll().stream().map(c -> c.id()).toList();
        // Delete all courts
        for (AggregateId courtId : courtIds) {
            courtRepository.deleteById(courtId);
        }
        // Delete all facilities
        for (AggregateId facilityId : facilityIds) {
            facilityRepository.deleteById(facilityId);
        }

        List<AggregateId> allIds = new ArrayList<>();
        allIds.addAll(facilityIds);
        allIds.addAll(courtIds);
        // Delete images
        return imageOperationPort.deleteByRelationIds(allIds);
    }
}
