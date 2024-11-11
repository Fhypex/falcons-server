package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ContactOperationsPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class CreateFacilityCommandHandler implements CommandHandler<CreateFacility, FacilityView> {

    private final FacilityFactory factory;
    private final ContactOperationsPort contactOperationsPort;
    private final FacilityService service;
    private final FacilityRepository facilityRepository;

    @Override
    public FacilityView handle(CreateFacility command) {

        // İlgili contact bilgilerini doğrula
        if (!contactOperationsPort.exists(command.contactDetails())) {
            throw new IllegalArgumentException("Contact details do not exist");
        }

        // Facility nesnesini oluştur
        Facility facility = factory.create(
                AggregateId.from(command.userId()),
                command.facilityName(),
                command.phoneNumber(),
                command.facilityDescription(),
                new Location(command.latitude(), command.longitude()),
                command.contactDetails(),
                new FacilityCapacity(command.courtCount())
        );

        // Veritabanına facility kaydet
        Facility savedFacility = facilityRepository.save(facility);

        // Facility verisini frontend için view olarak dönüştür
        return service.denormalize(savedFacility);
    }
}
