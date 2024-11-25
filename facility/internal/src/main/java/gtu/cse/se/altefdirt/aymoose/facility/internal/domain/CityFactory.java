package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.Random;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;


@Component
public class CityFactory {

    private final Random random = new Random();

    private Long generateId() {
        Long fPrime = 31L;
        Long sPrime = 17L;
        Long result = 1L;
        result = fPrime * random.nextLong(1, 10000);
        result = sPrime * result + random.nextLong(1, 100000);
        return result;
    }

    public City create(String name) {
        return new City(generateId(), name);
    }

    public City load(Long id,
            String name) {
        return new City(id, name);
    }
}
