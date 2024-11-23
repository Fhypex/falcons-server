package gtu.cse.se.altefdirt.aymoose.court.internal.application.model;

import java.time.Instant;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import lombok.Builder;

@Builder
public record CourtView (
    String id,
    String facilityId,
    String name,
    String description,
    Integer height,
    Integer width,
    Integer capacity,
    Instant openTime,
    Instant closeTime,
    String location,
    boolean isActive,
    List<List<String>> amenities,
    List<String> images
)
{
    public CourtView (Court court, List<List<String>> amenities, List<String> images) {
        this(court.id().value(), 
             court.facilityId().value(), 
             court.details().name(), 
             court.details().description(), 
             court.measurements().height(), 
             court.measurements().width(), 
             court.capacity().value(), 
             court.workHours().openTime(), 
             court.workHours().closeTime(), 
             court.location().value(),
             court.isActive(),
             amenities, 
             images);
    }
}
