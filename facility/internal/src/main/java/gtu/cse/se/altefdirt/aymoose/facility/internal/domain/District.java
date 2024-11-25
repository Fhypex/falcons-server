package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gtu.cse.se.altefdirt.aymoose.shared.domain.SimpleAggregateRoot;
import lombok.Getter;

@Getter
public class District extends SimpleAggregateRoot{
    
    private Long cityId;
    private String name;

    @JsonIgnore
    private boolean inUse;

    public District(Long id, Long cityId, String name, boolean inUse) {
        super(id);
        this.cityId = cityId;
        this.name = name;
        this.inUse = inUse;
    }

    public String name() {
        return name;
    }

    public Long cityId() {
        return cityId;
    }

    public boolean inUse() {
        return inUse;
    }

    @JsonIgnore
    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }


    public Map<String, Object> toMap() {
        return Map.of(
                "id", id(),
                "name", name);
    }
}
