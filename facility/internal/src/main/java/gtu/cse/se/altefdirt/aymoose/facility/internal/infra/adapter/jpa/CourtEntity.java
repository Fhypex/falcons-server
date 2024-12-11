package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.UUID;
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
    private UUID id;
    private UUID userId;
    private UUID facilityId;
    private String name;
    private String description;
    private int height;
    private int width;
    private int capacity;
    private int price;
}
