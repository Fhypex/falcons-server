package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
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
    private Double latitude;
    private Double longitude;
    private String contactDetails;
    private int courtCount;
    private boolean isActive;

    public static FacilityEntity from(Facility facility) {
        return FacilityEntity.builder()
            .id(facility.id().value())
            .userId(facility.getUserId().value())
            .facilityName(facility.getFacilityName())
            .phoneNumber(facility.getPhoneNumber())
            .facilityDescription(facility.getFacilityDescription())
            .latitude(facility.getLocation().latitude())
            .longitude(facility.getLocation().longitude())
            .contactDetails(facility.getContactDetails())
            .courtCount(facility.getCourtCount().value())
            .isActive(facility.isActive())
            .build();
    }
}
