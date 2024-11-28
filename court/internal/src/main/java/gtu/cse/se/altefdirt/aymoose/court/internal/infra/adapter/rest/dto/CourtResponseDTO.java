package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.rest.dto;

import java.time.Instant;
import java.util.List;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.CourtView;
import lombok.Builder;

@Builder
public record CourtResponseDTO(
    String id,
    String name,
    String description,
    Integer height,
    Integer width,
    int capacity,
    List<String> images
)
{
    public static CourtResponseDTO fromView(CourtView courtView) {
        return CourtResponseDTO.builder()
            .id(courtView.id())
            .name(courtView.name())
            .description(courtView.description())
            .height(courtView.height())
            .width(courtView.width())
            .capacity(courtView.capacity())
            .images(courtView.images())
            .build();
    }
}