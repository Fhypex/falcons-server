package gtu.cse.se.altefdirt.aymoose.court.internal.domain;

import java.util.List;
import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface CourtRepository {
    
    Court save(Court court);

    Optional<Court> findById(AggregateId id);

    List<Court> findAll();

}
