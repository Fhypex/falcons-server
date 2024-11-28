package gtu.cse.se.altefdirt.aymoose.court.internal.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.CourtView;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.service.CourtService;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class CourtServiceImpl implements CourtService {

    private final ImageOperationPort imageOperationPort;

    @Override
    public CourtView denormalize(Court court) {
        List<ImageData> images = imageOperationPort.findByRelationId(court.id());

        List<String> imagePaths = images.stream().map(image -> image.id()).collect(Collectors.toList());

        return CourtView.builder()
                .id(court.id().value())
                .name(court.details().name())
                .description(court.details().description())
                .height(court.measurements().height())
                .width(court.measurements().width())
                .capacity(court.capacity().value())
                .images(imagePaths)
                .build();
    }
}
