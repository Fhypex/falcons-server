package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.provider;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.court.api.provider.CourtProvider;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtData;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtRichData;
import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
class CourtProviderImpl implements CourtProvider {

    private final CourtRepository courtRepository;
    private final ImageOperationPort imageOperationPort;

    private CourtData build(Court court) {
        return CourtData.builder()
                .id(court.id().value())
                .facilityId(court.getFacilityId().value())
                .name(court.getDetails().name())
                .description(court.getDetails().description())
                .height(court.getMeasurements().height())
                .width(court.getMeasurements().width())
                .capacity(court.getCapacity().value())
                .build();
    }

    private CourtRichData buildRich(Court court) {

        List<String> imageUrls = imageOperationPort.findByRelationId(court.id()).stream()
                .map(ImageData::url).collect(Collectors.toUnmodifiableList());
        return CourtRichData.builder()
                .id(court.id().value())
                .facilityId(court.getFacilityId().value())
                .name(court.getDetails().name())
                .description(court.getDetails().description())
                .height(court.getMeasurements().height())
                .width(court.getMeasurements().width())
                .capacity(court.getCapacity().value())
                .price(court.getPrice().value())
                .imageUrls(imageUrls)
                .build();
    }

    @Override
    public int deleteById(AggregateId id) {
        // delete images
        imageOperationPort.deleteByRelationId(id);
        // delete court
        return courtRepository.deleteById(id);
    }

    @Override
    public CourtData getCourtById(AggregateId id) {
        Court court = courtRepository.findById(id).get();
        return build(court);
    }

    @Override
    public List<CourtData> getCourtsByFacilityId(AggregateId facilityId) {
        List<Court> courts = courtRepository.findByFacilityId(facilityId);
        return courts.stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public int deleteByFacilityId(AggregateId facilityId) {
        // delete images
        imageOperationPort.deleteByRelationId(facilityId);
        // delete courts
        return courtRepository.deleteByFacilityId(facilityId);
    }

    @Override
    public List<CourtRichData> getCourtsByFacilityIdRich(AggregateId facilityId) {
        List<Court> courts = courtRepository.findByFacilityId(facilityId);
        return courts.stream().map(this::buildRich).collect(Collectors.toUnmodifiableList());
    }
    
}
