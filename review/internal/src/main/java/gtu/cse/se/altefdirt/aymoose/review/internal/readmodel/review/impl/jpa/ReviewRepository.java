package gtu.cse.se.altefdirt.aymoose.review.internal.readmodel.review.impl.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gtu.cse.se.altefdirt.aymoose.review.internal.shared.jpa.ReviewEntity;
import jakarta.persistence.EntityManager;

interface ReviewRepository extends JpaRepository<ReviewEntity, String> {

    List<ReviewEntity> findByUserId(String userId);

    List<ReviewEntity> findByFacilityId(String facilityId);

    List<ReviewEntity> findByUserIdAndFacilityId(String userId, String facilityId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.userId = ?1 AND r.facilityId = ?2 AND r.rating <= ?3")
    List<ReviewEntity> findByUserIdAndFacilityIdAndRatingLessThan(String userId, String facilityId, short rating);

    @Query("SELECT r FROM ReviewEntity r WHERE r.userId = ?1 AND r.facilityId = ?2 AND r.rating >= ?3")
    List<ReviewEntity> findByUserIdAndFacilityIdAndRatingGreaterThan(String userId, String facilityId, short rating);

    @Query("SELECT r FROM ReviewEntity r WHERE r.userId = ?1 AND r.facilityId = ?2 AND r.rating = ?3")
    List<ReviewEntity> findByUserIdAndFacilityIdAndRatingEqual(String userId, String facilityId, short rating);
}
