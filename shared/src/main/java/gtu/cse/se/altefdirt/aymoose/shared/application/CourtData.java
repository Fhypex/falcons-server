package gtu.cse.se.altefdirt.aymoose.shared.application;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.Builder;

@Builder
public record CourtData(AggregateId id,
                AggregateId facilityId,
                String name,
                String description,
                Integer height,
                Integer width,
                Integer capacity,
                Integer price) {
}
