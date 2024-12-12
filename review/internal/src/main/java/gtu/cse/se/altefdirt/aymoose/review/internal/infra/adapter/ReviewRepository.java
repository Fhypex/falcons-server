package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Rating;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewRepository;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.JpaReviewRepository;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.mapper.ReviewMapper;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReviewRepositoryImpl implements ReviewRepository {

    private final JpaReviewRepository jpaRepository;
    private final ReviewMapper mapper;

    @Override
    public List<Review> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Review> findByIds(List<AggregateId> ids) {
        return jpaRepository.findAllById(ids.stream().map(AggregateId::value).toList()).stream().map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByIds(List<AggregateId> ids) {
        return jpaRepository.existsByIds(
                ids.stream().map(AggregateId::value).toList(),
                ids.size());
    }

    @Override
    public Optional<Review> findById(AggregateId id) {
        return Optional.of(mapper.toDomain(jpaRepository.findById(id.value()).get()));
    }

    @Override
    public Review save(Review review) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(review)));
    }

    @Override
    public int deleteById(AggregateId id) {
        jpaRepository.deleteById(id.value());
        return 1;
    }

    @Override
    public boolean existsById(AggregateId id) {
        return jpaRepository.existsById(id.value());
    }

    @Override
    public List<Review> findByFacilityId(AggregateId facilityId) {
        return jpaRepository.findByFacilityId(facilityId.value()).stream().map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Review> findByUserId(AggregateId userId) {
        return jpaRepository.findByUserId(userId.value()).stream().map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Review> findByUserIdAndFacilityId(AggregateId userId, AggregateId facilityId) {
        return jpaRepository.findByUserIdAndFacilityId(userId.value(), facilityId.value()).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Review> findByUserIdFacilityIdAndRatingEqual(AggregateId userId, AggregateId facilityId,
            Rating rating) {
        return jpaRepository
                .findByUserIdAndFacilityIdAndRatingEqual(userId.value(), facilityId.value(), rating.leading())
                .stream()
                .map(mapper::toDomain)
                .toList();

    }

    @Override
    public List<Review> findByUserIdFacilityIdAndRatingLesserThan(AggregateId userId, AggregateId facilityId,
            Rating rating) {
        return jpaRepository
                .findByUserIdAndFacilityIdAndRatingLessThan(userId.value(), facilityId.value(), rating.leading())
                .stream()
                .map(mapper::toDomain)
                .toList();

    }

    @Override
    public List<Review> findByUserIdFacilityIdAndRatingGreaterThan(AggregateId userId, AggregateId facilityId,
            Rating rating) {
        return jpaRepository
                .findByUserIdAndFacilityIdAndRatingGreaterThan(userId.value(), facilityId.value(), rating.leading())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
