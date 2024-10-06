package gtu.cse.se.altefdirt.aymoose.review.internal.readmodel.review.query;

import gtu.cse.se.altefdirt.aymoose.shared.readmodel.Query;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.review.internal.readmodel.review.Review;

@Query
public interface SortCreationTime {

    public List<Review> queryAscending();

    public List<Review> queryDescending();
}