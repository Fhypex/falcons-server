package gtu.cse.se.altefdirt.aymoose.facility.internal.application.service;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.CourtView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Court;

public interface CourtService {

    CourtView denormalize(Court court);
}
