package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.FacilityEntity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Address;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import gtu.cse.se.altefdirt.aymoose.shared.domain.PhoneNumber;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FacilityMapper {

    private final FacilityFactory factory;

    public FacilityEntity toEntity(Facility facility) {
        return FacilityEntity.builder()
                .id(facility.id().value())
                .userId(facility.userId().value())
                .name(facility.name())
                .description(facility.description())
                .phoneNumber(facility.phoneNumber().value())
                .location(facility.location().value())
                .cityId(facility.address().cityId())
                .districtId(facility.address().districtId())
                .fullAddress(facility.address().fullAddress())
                .contactDetails(facility.getContactDetails())
                .openTime(facility.workHours().openTime())
                .closeTime(facility.workHours().closeTime())
                .amenities(facility.amenities().stream().map(AggregateId::value).collect(Collectors.toSet()))
                .isActive(facility.isActive())
                .build();
    }

    public Facility toDomain(FacilityEntity entity) {
        return factory.load(
                AggregateId.fromUUID(entity.getId()),
                AggregateId.fromUUID(entity.getUserId()),
                new PhoneNumber(entity.getPhoneNumber()),
                entity.getName(),
                entity.getDescription(),
                new Address(entity.getCityId(), entity.getDistrictId(), entity.getFullAddress()),
                new Location(entity.getLocation()),
                entity.getContactDetails(),
                new WorkHours(entity.getOpenTime(), entity.getCloseTime()),
                entity.getAmenities().stream().map(AggregateId::fromUUID).toList(),
                entity.isActive());
    }
}
