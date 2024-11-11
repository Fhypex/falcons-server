package gtu.cse.se.altefdirt.aymoose.review.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.command.CreateReview;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReservationData;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.ReservationOperationsPort;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.Save;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.service.ReviewService;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Comment;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Rating;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewFactory;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class CreateReviewCommandHandler implements CommandHandler<CreateReview, ReviewView> {

    private final ReviewFactory factory;
    private final ReservationOperationsPort reservationOperationPort;
    private final ReviewService service;
    private final Save save;

    @Override
    public ReviewView handle(CreateReview command) {

        ReservationData reservationData = reservationOperationPort.getReservationData(command.reservationId(), command.userId());

        if (service.isReviewExist(command.reservationId(), command.userId())) {
            throw new IllegalArgumentException("Review already exists");
        }
        Review review = factory.create(AggregateId.from(command.reservationId()), 
                                       AggregateId.from(command.userId()), 
                                       AggregateId.from(reservationData.facilityId()), 
                                       new Comment(command.title(), command.content()),
                                       new Rating(command.rating()));

        return service.denormalize(save.save(review));
    }
}