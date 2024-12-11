package gtu.cse.se.altefdirt.aymoose.review.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.command.CreateReview;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Comment;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Rating;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewFactory;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class CreateReviewCommandHandler implements CommandHandler<CreateReview, Review> {

    private final ReviewFactory factory;
    private final ReviewRepository repository;

    @Override
    public Review handle(CreateReview command) {

        AggregateId reservationId = AggregateId.fromString("11111111-1111-1111-1111-11111111");

        /*
         * ReservationData reservationData =
         * reservationOperationPort.getReservationData(
         * reservationId, AggregateId.fromUUID(command.userId()));
         * if (service.isReviewExist(AggregateId.fromUUID(command.reservationId()),
         * AggregateId.fromUUID(command.userId()))) {
         * throw new IllegalArgumentException("Review already exists");
         */

        Review review = factory.create(reservationId,
                AggregateId.fromUUID(command.userId()),
                AggregateId.fromUUID(command.facilityId()),
                new Comment(command.title(), command.content()),
                new Rating(command.rating()));

        return repository.save(review);
    }
}