package gtu.cse.se.altefdirt.aymoose.shared.domain;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    T save(T city);

    Optional<T> findById(ID id);

    List<T> findAll();

    List<T> findByIds(List<ID> ids);

    boolean existsById(ID id);

    int deleteById(ID id);

    boolean existsByIds(List<ID> ids);
}
