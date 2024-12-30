package gtu.cse.se.altefdirt.aymoose.shared.application;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.Builder;

@Builder
public record FacilityData(AggregateId id,
                AggregateId ownerId,
                String name,
                String description,
                Integer height,
                Integer width,
                WorkHours workHours,
                boolean isActive) {
}
