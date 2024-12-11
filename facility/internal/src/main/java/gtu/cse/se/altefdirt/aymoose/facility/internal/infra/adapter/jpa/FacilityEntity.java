package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.List;
import java.util.UUID;

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
    private UUID id;
    private UUID userId;
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
    private List<UUID> amenities;
    private boolean isActive;
}
