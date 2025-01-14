package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findById(UUID userId);

    List<AccountEntity> findAll();

    @Query("SELECT COUNT(a) = :size FROM CourtEntity a WHERE a.id IN :ids")
    boolean existsByIds(List<UUID> ids, int size);
}