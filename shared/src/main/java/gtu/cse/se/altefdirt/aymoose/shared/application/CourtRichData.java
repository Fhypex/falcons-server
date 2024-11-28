package gtu.cse.se.altefdirt.aymoose.shared.application;

import java.util.List;

import lombok.Builder;

@Builder
public record CourtRichData(String id,
        String facilityId,
        String name,
        String description,
        Integer height,
        Integer width,
        Integer capacity,
        Integer price,
        List<String> imageUrls) {
}
