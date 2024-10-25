package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Capacity;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtDetails;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtFactory;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Measurements;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.WorkHours;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.CreatedAt;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import gtu.cse.se.altefdirt.aymoose.shared.domain.UpdatedAt;
import io.micrometer.core.instrument.Measurement;
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
    private String facilityId;
    private String name;
    private String description;
    private int height;
    private int width;
    private int capacity;
    private Instant openTime;
    private Instant closeTime;
    private Double latitude;
    private Double longitude;

    public static CourtEntity from(Court court) {
        return CourtEntity.builder()
            .id(court.id().value())
            .facilityId(court.getFacilityId().value())
            .name(court.name())
            .description(court.description())
            .height(court.getMeasurements().height())
            .width(court.getMeasurements().width())
            .capacity(court.getCapacity().capacity())
            .openTime(court.getWorkHours().openTime())
            .closeTime(court.getWorkHours().closeTime())
            .latitude(court.getLocation().latitude())
            .longitude(court.getLocation().longitude())
            .build();
    }

    public Court toDomain(CourtFactory factory) {
        return factory.load(
            AggregateId.from(this.id),
            AggregateId.from(this.facilityId),
            new CourtDetails(this.name, this.description),
            new Measurements(this.height, this.width),
            new Capacity(this.capacity),
            new WorkHours(openTime, closeTime),
            new Location(this.latitude, this.longitude)
        );
    }
}
