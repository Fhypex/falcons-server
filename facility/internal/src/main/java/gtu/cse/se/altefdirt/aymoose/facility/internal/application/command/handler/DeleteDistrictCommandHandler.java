package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteDistrict;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class DeleteDistrictCommandHandler implements CommandHandler<DeleteDistrict, Integer> {

    private final DistrictRepository districtRepository;
    private final FacilityRepository facilityRepository;
    @Override
    public Integer handle(DeleteDistrict command) {

        if(facilityRepository.hasFacilityByDistrictId(command.id())) {
            throw new IllegalStateException("District has facilities. Cannot delete.");
        }

        return districtRepository.deleteById(command.id());
    }
}
