package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa;

import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
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
public class CourtEntity {

    @Id
    private String id;
    private String ownerId;
    private String facilityId;
    private String name;
    private String description;
    private int height;
    private int width;
    private int capacity;

    public static CourtEntity from(Court court) {
        return CourtEntity.builder()
                .id(court.id().value())
                .ownerId(court.ownerId().value())
                .facilityId(court.getFacilityId().value())
                .name(court.details().name())
                .description(court.details().description())
                .height(court.getMeasurements().height())
                .width(court.getMeasurements().width())
                .capacity(court.getCapacity().value())
                .build();
    }
}
