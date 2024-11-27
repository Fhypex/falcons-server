package gtu.cse.se.altefdirt.aymoose.shared.application;

import lombok.Builder;

@Builder
public record ImageData(String id, String url, Long size, String extension) {
}
