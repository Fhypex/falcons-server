package gtu.cse.se.altefdirt.aymoose.court.internal.application.port;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.FacilityData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface FacilityOperationPort {

    FacilityData find(AggregateId facilityId);

    FacilityData save(AggregateId relationId, MultipartFile image);

    int delete(AggregateId imageId);

    List<FacilityData> findByRelationId(AggregateId relationId);

    int deleteByRelationId(AggregateId relationId);
}
