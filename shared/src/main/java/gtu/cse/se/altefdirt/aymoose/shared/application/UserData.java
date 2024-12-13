package gtu.cse.se.altefdirt.aymoose.shared.application;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

public record UserData(AggregateId id, FullName fullName, String imageUrl) {

}
