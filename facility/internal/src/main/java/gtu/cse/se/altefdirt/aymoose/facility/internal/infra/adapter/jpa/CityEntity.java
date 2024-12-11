package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

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
public class CityEntity {

    @Id
    private Long id;
    private String name;
}
