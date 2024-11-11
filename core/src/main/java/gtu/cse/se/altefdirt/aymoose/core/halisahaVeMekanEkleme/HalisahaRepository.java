package gtu.cse.se.altefdirt.aymoose.core.halisahaVeMekanEkleme;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HalisahaRepository extends JpaRepository<Halisaha, Long> {
    List<Halisaha> findByMekanId(Long mekanId); 
}
