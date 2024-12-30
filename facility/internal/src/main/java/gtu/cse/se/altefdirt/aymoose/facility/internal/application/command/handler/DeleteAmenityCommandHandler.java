package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteAmenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class DeleteAmenityCommandHandler implements CommandHandler<DeleteAmenity, Integer> {

    private final FacilityRepository facilityRepository;
    private final AmenityRepository amenityRepository;
    private final ImageOperationPort imageOperationPort;

    @Override
    public Integer handle(DeleteAmenity command) {

        // Delete amenity
        amenityRepository.deleteById(AggregateId.fromUUID(command.id()));

        // Delete amenity from facilities
        facilityRepository.deleteAmenityFromAllFacilities(AggregateId.fromUUID(command.id()));

        // Delete images
        return imageOperationPort.deleteByRelationId(AggregateId.fromUUID(command.id()));
    }
}
