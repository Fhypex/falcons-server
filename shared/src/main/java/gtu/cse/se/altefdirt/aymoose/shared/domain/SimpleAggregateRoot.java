package gtu.cse.se.altefdirt.aymoose.shared.domain;

import jakarta.validation.constraints.NotNull;

public abstract class SimpleAggregateRoot {

  private final Long aggregateId;
  private int baseVersion;

  protected SimpleAggregateRoot(@NotNull Long aggregateId) {
    this.aggregateId = aggregateId;
    this.baseVersion = 0;
  }

  public Long id() {
    return aggregateId;
  }

  public int baseVersion() {
    return baseVersion;
  }

  protected int nextVersion() {
    return baseVersion + 1;
  }

  @Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof SimpleAggregateRoot) {
			SimpleAggregateRoot other = (SimpleAggregateRoot) obj;
			if (other.aggregateId == null)
				return false;
			return other.aggregateId.equals(aggregateId);
		}
		
		return false;
	}
	
	@Override
	public final int hashCode() {	
		return aggregateId.hashCode();
	}
}
