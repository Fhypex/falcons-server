package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateCity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.City;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class CreateCityCommandHandler implements CommandHandler<CreateCity, City> {

    private final CityRepository cityRepository;
    private final CityFactory factory;

    @Override
    public City handle(CreateCity command) {

        City city = factory.create(command.name());
        City savedCity = cityRepository.save(city);
        return savedCity;
    }
}
