package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

@Getter
public class Account extends BaseAggregateRoot {
   
    public Account(AggregateId id, 
                 String accountname,
                 FullName fullName,
                 String profilePicture, 
                 Instant createdAt,  
                 Boolean isActive) {
        super(id);
        this.username = username;
        this.fullName = fullName;
        this.profilePicture = profilePicture;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    private String username;
    private FullName fullName;
    private String profilePicture;
    private Instant createdAt;
    private Boolean isActive;


    public String username() {
        return username;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void disable() {
        this.isActive = false;
    }

    public void enable() {
        this.isActive = true;
    }

    public void updateProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public FullName fullName() {
        return fullName;
    }

    public String profilePicture() {
        return profilePicture;
    }

    public Instant createdAt() {
        return createdAt;
    }
}
