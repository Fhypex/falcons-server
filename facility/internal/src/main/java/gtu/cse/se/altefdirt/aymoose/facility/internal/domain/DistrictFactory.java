package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class DistrictFactory {

    private final Random random = new Random();

    private Long generateId() {
        Long fPrime = 31L;
        Long sPrime = 17L;
        Long result;
        result = fPrime * random.nextLong(1, 10000);
        result = sPrime * result + random.nextLong(1, 100000);
        return result;
    }

    public District create(Long cityId, String name) {
        return new District(generateId(), cityId, name);
    }

    public District load(Long id, Long cityId,
            String name) {
        return new District(id, cityId, name);
    }
}
