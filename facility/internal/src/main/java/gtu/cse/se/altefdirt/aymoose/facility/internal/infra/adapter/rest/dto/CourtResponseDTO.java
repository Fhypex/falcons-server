package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto;

import java.util.List;
import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.CourtView;
import lombok.Builder;

@Builder
public record CourtResponseDTO(
        UUID id,
        String name,
        String description,
        Integer height,
        Integer width,
        int capacity,
        Integer price,
        List<String> imageUrls) {
    public static CourtResponseDTO fromView(CourtView courtView) {
        return CourtResponseDTO.builder()
                .id(courtView.id())
                .name(courtView.name())
                .description(courtView.description())
                .height(courtView.height())
                .width(courtView.width())
                .capacity(courtView.capacity())
                .imageUrls(courtView.imageUrls())
                .price(courtView.price())
                .build();
    }
}