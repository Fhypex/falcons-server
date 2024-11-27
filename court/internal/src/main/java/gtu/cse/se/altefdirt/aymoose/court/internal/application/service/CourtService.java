package gtu.cse.se.altefdirt.aymoose.court.internal.application.service;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.CourtView;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;

public interface CourtService {

    CourtView denormalize(Court court);
}
