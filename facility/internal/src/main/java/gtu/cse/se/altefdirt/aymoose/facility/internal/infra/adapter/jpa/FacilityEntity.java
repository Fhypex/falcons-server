package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
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
    private String facilityName;
    private String phoneNumber;
    private String facilityDescription;
    private String city;
    private String district;
    private String contactDetails;
    private int courtCount;
    private String location;
    private boolean isActive;

    public static FacilityEntity from(Facility facility) {
        return FacilityEntity.builder()
            .id(facility.id().value())
            .userId(facility.getUserId().value())
            .facilityName(facility.getFacilityName())
            .phoneNumber(facility.getPhoneNumber())
            .facilityDescription(facility.getFacilityDescription())
            .location(facility.getLocation())
            .city(facility.getCity())
            .district(facility.getDistrict())
            .contactDetails(facility.getContactDetails())
            .courtCount(facility.getCourtCount().value())
            .isActive(facility.isActive())
            .build();
    }
}
