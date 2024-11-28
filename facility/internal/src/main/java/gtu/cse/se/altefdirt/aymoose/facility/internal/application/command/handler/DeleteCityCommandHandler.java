package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteCity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class DeleteCityCommandHandler implements CommandHandler<DeleteCity, Integer> {

    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;
    private final FacilityRepository facilityRepository;
    @Override
    public Integer handle(DeleteCity command) {

        List<District> districts = districtRepository.findByCityId(command.id());

        for(District district : districts) {
            if(facilityRepository.hasFacilityByDistrictId(district.id())) {
                throw new IllegalStateException("City has districts with facilities. Cannot delete.");
            }
        }

        for(District district : districts) {
            districtRepository.deleteById(district.id());
        }

        return cityRepository.deleteById(command.id());
    }
}
