package gtu.cse.se.altefdirt.aymoose.shared.application;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.Builder;

@Builder
public record ImageData(AggregateId id, String url, Long size, String extension) {
}
