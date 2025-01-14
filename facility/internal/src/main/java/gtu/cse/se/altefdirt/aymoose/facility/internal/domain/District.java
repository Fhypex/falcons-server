package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.HashMap;
import java.util.Map;

import gtu.cse.se.altefdirt.aymoose.shared.domain.SimpleAggregateRoot;
import lombok.Getter;

@Getter
public class District extends SimpleAggregateRoot {

    private Long cityId;
    private String name;

    public District(Long id, Long cityId, String name) {
        super(id);
        this.cityId = cityId;
        this.name = name;
    }

    public String name() {
        return name;
    }

    public Long cityId() {
        return cityId;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id());
        map.put("cityId", cityId);
        map.put("name", name);
        return map;
    }
}
