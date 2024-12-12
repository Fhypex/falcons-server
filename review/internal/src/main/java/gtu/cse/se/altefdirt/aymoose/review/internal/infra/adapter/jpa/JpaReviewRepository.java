package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaReviewRepository extends JpaRepository<ReviewEntity, UUID> {

    @Query("SELECT COUNT(r) FROM ReviewEntity r WHERE r.reservationId = ?1 AND r.userId = ?2")
    int countByReservationIdAndUserId(UUID reservationId, UUID userId);

    List<ReviewEntity> findByUserId(UUID userId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.facilityId = ?1")
    List<ReviewEntity> findByFacilityId(UUID facilityId);

    List<ReviewEntity> findByUserIdAndFacilityId(UUID userId, UUID facilityId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.userId = ?1 AND r.facilityId = ?2 AND r.rating <= ?3")
    List<ReviewEntity> findByUserIdAndFacilityIdAndRatingLessThan(UUID userId, UUID facilityId, short rating);

    @Query("SELECT r FROM ReviewEntity r WHERE r.userId = ?1 AND r.facilityId = ?2 AND r.rating >= ?3")
    List<ReviewEntity> findByUserIdAndFacilityIdAndRatingGreaterThan(UUID userId, UUID facilityId, short rating);

    @Query("SELECT r FROM ReviewEntity r WHERE r.userId = ?1 AND r.facilityId = ?2 AND r.rating = ?3")
    List<ReviewEntity> findByUserIdAndFacilityIdAndRatingEqual(UUID userId, UUID facilityId, short rating);

    @Query("SELECT COUNT(a) = :size FROM ReviewEntity a WHERE a.id IN :ids")
    boolean existsByIds(List<UUID> ids, int size);
}