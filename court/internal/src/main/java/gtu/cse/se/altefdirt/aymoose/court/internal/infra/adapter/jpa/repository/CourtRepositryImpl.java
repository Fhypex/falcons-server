package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtFactory;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa.CourtEntity;
import gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa.JpaCourtRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CourtRepositryImpl implements CourtRepository {

    private final JpaCourtRepository jpacourtRepository;
    private final CourtFactory courtFactory;

    @Override
    public Court save(Court court) {
        return jpacourtRepository.save(CourtEntity.from(court)).toDomain(courtFactory);
    }

    @Override
    public Court findById(AggregateId id) {
        return jpacourtRepository.findById(id.value()).get().toDomain(courtFactory);
    }

    @Override
    public List<Court> findAll() {
        return jpacourtRepository.findAll().stream().map(entity -> entity.toDomain(courtFactory)).collect(Collectors.toList());
    }
}