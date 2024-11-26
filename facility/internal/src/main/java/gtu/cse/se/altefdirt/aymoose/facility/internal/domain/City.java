package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.SimpleAggregateRoot;

public class City extends SimpleAggregateRoot{
    
    private String name;

    public City(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String name() {
        return name;
    }
}
