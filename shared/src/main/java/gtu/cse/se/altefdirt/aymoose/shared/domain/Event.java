package gtu.cse.se.altefdirt.aymoose.shared.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public abstract class Event {

  private final UUID aggregateId;
  private final ZonedDateTime timestamp;
  private final int version;

  protected Event(@NotNull UUID aggregateId, 
                  @NotNull ZonedDateTime timestamp, int version) {
    this.aggregateId = aggregateId;
    this.timestamp = timestamp;
    this.version = version;
  }

  public UUID getAggregateId() {
    return aggregateId;
  }

  public ZonedDateTime getTimestamp() {
    return this.timestamp;
  }

  public int getVersion() {
    return version;
  }
}
