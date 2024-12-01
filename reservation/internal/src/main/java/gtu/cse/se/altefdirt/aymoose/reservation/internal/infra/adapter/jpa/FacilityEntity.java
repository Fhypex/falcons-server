package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class FacilityEntity {

    @Id
    private String id;
    private String userId;
    private String name;
    private String description;
    private String phoneNumber;
    private Long cityId;
    private Long districtId;
    private String fullAddress;
    private String location;
    private String contactDetails;
    private int openTime;
    private int closeTime;
    @ElementCollection(targetClass = String.class, fetch = jakarta.persistence.FetchType.EAGER)
    private List<String> amenities;
    private boolean isActive;

    public static FacilityEntity from(Facility facility) {
        return FacilityEntity.builder()
                .id(facility.id().value())
                .userId(facility.userId().value())
                .name(facility.name())
                .description(facility.description())
                .phoneNumber(facility.phoneNumber().value())
                .location(facility.location().value())
                .cityId(facility.address().cityId())
                .districtId(facility.address().districtId())
                .fullAddress(facility.address().fullAddress())
                .contactDetails(facility.getContactDetails())
                .openTime(facility.workHours().openTime())
                .closeTime(facility.workHours().closeTime())
                .amenities(facility.amenities().stream().map(amenity -> amenity.id()).toList())
                .isActive(facility.isActive())
                .build();
    }
}
