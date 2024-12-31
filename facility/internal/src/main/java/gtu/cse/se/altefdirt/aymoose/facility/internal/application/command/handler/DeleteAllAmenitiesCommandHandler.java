package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import java.util.ArrayList;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteAllAmenities;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteAllFacilityAndCourts;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityRepository;
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
public class DeleteAllAmenitiesCommandHandler implements CommandHandler<DeleteAllAmenities, Integer> {

    private final FacilityRepository facilityRepository;
    private final ImageOperationPort imageOperationPort;
    private final AmenityRepository amenityRepository;

    @Override
    @Transactional
    public Integer handle(DeleteAllAmenities command) {

        List<AggregateId> amenityIds = amenityRepository.findAll().stream().map(amenity -> amenity.id()).toList();

        for (AggregateId amenityId : amenityIds) {
            int deleted = amenityRepository.deleteById(amenityId);
            log.debug("Deleted amenity with id: {}", amenityId);
            facilityRepository.deleteAmenityFromAllFacilities(amenityId);
            log.debug("Deleted amenity from all facilities with id: {}", amenityId);
        }
        // Delete images
        return imageOperationPort.deleteByRelationIds(amenityIds);
    }
}
