package gtu.cse.se.altefdirt.aymoose.favorites.internal.infra.adapter.jpa;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
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
public class FavoritesEntity {
    
    @Id
    private String id;
    private String userId;
    private String facilityId;

    public static FavoritesEntity from(AggregateId id, AggregateId userId, AggregateId facilityId) {
        return FavoritesEntity.builder()
            .id(id.value())
            .userId(userId.value())
            .facilityId(facilityId.value())
            .build();
    }
}
