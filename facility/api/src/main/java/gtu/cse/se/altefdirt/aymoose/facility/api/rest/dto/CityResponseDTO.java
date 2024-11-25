package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import java.util.List;
import java.util.Map;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.CityView;
import lombok.Builder;

@Builder
public record CityResponseDTO(
        Long id,
        String name,
        List<Map<String, Object>> districts) {
    public static CityResponseDTO fromView(CityView view) {
        return CityResponseDTO.builder()
                .id(view.id())
                .name(view.name())
                .districts(view.districts())
                .build();
    }
}
