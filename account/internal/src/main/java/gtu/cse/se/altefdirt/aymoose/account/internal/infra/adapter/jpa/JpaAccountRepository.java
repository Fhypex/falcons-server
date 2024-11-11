package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, String> {

    Optional<AccountEntity> findById(String userId);

    List<AccountEntity> findAll();

}