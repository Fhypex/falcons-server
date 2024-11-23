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
    private String ownerId;
    private String name;
    private String description;
    private String phoneNumber;
    private String city;
    private String district;
    private String fullAddress;
    private String location;
    private String contactDetails;
    private boolean isActive;

    public static FacilityEntity from(Facility facility) {
        return FacilityEntity.builder()
                .id(facility.id().value())
                .ownerId(facility.ownerId().value())
                .name(facility.name())
                .description(facility.description())
                .phoneNumber(facility.phoneNumber().value())
                .location(facility.location().value())
                .city(facility.address().city())
                .district(facility.address().district())
                .fullAddress(facility.address().fullAddress())
                .contactDetails(facility.getContactDetails())
                .isActive(facility.isActive())
                .build();
    }
}
