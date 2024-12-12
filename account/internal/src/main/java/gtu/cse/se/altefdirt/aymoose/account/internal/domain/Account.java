package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import lombok.Getter;

import java.time.Instant;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.CreatedAt;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

@Getter
public class Account extends BaseAggregateRoot {

    public Account(AggregateId id,
            FullName fullName,
            Instant createdAt,
            Boolean isActive) {
        super(id);
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    private AggregateId id;
    private FullName fullName;
    private Instant createdAt;
    private Boolean isActive;

    public Boolean isActive() {
        return isActive;
    }

    public void updateFirstName(String firstName) {
        this.fullName = FullName.of(firstName, this.fullName.lastName());
    }

    public void updateLastName(String lastName) {
        this.fullName = FullName.of(this.fullName.firstName(), lastName);
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

    public Instant createdAt() {
        return createdAt;
    }
}
