package gtu.cse.se.altefdirt.aymoose.favorites.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Favorites extends BaseAggregateRoot {

    private final AggregateId userId;
    private final AggregateId facilityId;

    public Favorites(AggregateId id, AggregateId userId, AggregateId facilityId) {
        // String token=HttpServletRequest.getHeader("Autharization").substring(7);
        super(id);
        this.userId = userId;
        this.facilityId = facilityId;
    }
}
