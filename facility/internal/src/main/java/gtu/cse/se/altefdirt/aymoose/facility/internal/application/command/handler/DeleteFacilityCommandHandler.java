package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.CourtOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class DeleteFacilityCommandHandler implements CommandHandler<DeleteFacility, Integer> {

    private final FacilityRepository facilityRepository;
    private final ImageOperationPort imageOperationPort;
    private final CourtOperationPort courtOperationPort;

    @Override
    public Integer handle(DeleteFacility command) {

        // Delete all courts of the facility
        courtOperationPort.deleteByFacilityId(AggregateId.from(command.id()));

        // Delete facility
        facilityRepository.deleteById(AggregateId.from(command.id()));

        // Delete images
        return imageOperationPort.deleteByRelationId(AggregateId.from(command.id()));
    }
}
