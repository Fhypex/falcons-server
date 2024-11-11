package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCourtRepository extends JpaRepository<CourtEntity, String> {

    List<CourtEntity> findAll();

    Optional<CourtEntity> findById(String id);

}