package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import lombok.Builder;

@Builder
public record FacilityListResponseDTO(
        String id,
        String name,
        String image,
        double rating,
        int reviews,
        String services,
        int price,
        String city,
        String district,
        String location) {
    public static FacilityListResponseDTO from(FacilityView facility) {
        return FacilityListResponseDTO.builder()
                .id(facility.id())
                .name(facility.facilityName())
                .image("https://tesiskirala.com/wp-content/uploads/2024/01/IMG-20190515-WA0000.jpg")
                .rating(4.5)
                .reviews(100)
                .services("6+6 · 7+7 · 8+8 Sahalar · Cafe · Ayakkabı")
                .price(150)
                .city(facility.city())
                .district(facility.district())
                .location("Cumhuriyet, Gebze Kocaeli")
                .build();
    }
}
