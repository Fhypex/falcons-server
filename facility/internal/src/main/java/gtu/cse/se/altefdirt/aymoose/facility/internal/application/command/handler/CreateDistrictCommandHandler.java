package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateDistrict;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class CreateDistrictCommandHandler implements CommandHandler<CreateDistrict, District> {

    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final DistrictFactory factory;

    @Override
    public District handle(CreateDistrict command) {

        log.info("CityId: {}", command.cityId());
        if(!cityRepository.existsById(command.cityId())) {
            throw new IllegalArgumentException("City not found");
        }

        if(districtRepository.existsByCityIdAndName(command.cityId(), command.name())) {
            throw new IllegalArgumentException("District already exists");
        }

        District district = factory.create(command.cityId(), command.name());
        return  districtRepository.save(district);
    }
}
