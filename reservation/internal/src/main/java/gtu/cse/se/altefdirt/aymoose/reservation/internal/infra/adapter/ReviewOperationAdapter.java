package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ReviewOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
class ReviewOperationAdapter implements ReviewOperationPort {

    @Override
    public int reviewCount(AggregateId userId) {
    
        return 2;
    }

    @Override
    public String rating(AggregateId userId) {
    
        return "5";
    }
}

