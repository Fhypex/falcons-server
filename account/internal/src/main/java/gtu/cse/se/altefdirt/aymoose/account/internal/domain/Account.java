package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import lombok.Getter;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.CreatedAt;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

@Getter
public class Account extends BaseAggregateRoot {
   
    public Account(AggregateId id, 
                 FullName fullName,
                 AggregateId imageId, 
                 CreatedAt createdAt,  
                 Boolean isActive)  {
        super(id);
        this.fullName = fullName;
        this.imageId = imageId;
        this.createdAt = createdAt;
        this.isActive = isActive;
    } 

    private AggregateId id;
    private FullName fullName;
    private AggregateId imageId;
    private CreatedAt createdAt;
    private Boolean isActive;

    public Boolean isActive() {
        return isActive;
    }

    public void disable() {
        this.isActive = false;
    }

    public void enable() {
        this.isActive = true;
    }

    public FullName fullName() {
        return fullName;
    }

    public AggregateId imageId() {
        return imageId;
    }

    public CreatedAt createdAt() {
        return createdAt;
    }
}
