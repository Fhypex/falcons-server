package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.List;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Address;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import gtu.cse.se.altefdirt.aymoose.shared.domain.PhoneNumber;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;

@Component
public class FacilityFactory {

    public Facility create(AggregateId userId,
            PhoneNumber phoneNumber,
            String name,
            String description,
            Address address,
            Location location,
            String contactDetails,
            WorkHours workHours,
            List<AggregateId> amenities,
            boolean isActive) {
        return new Facility(AggregateId.generate(), userId, phoneNumber, name, description, address, location, contactDetails, workHours, amenities, isActive);
    }

    public Facility load(AggregateId id,
            AggregateId userId,
            PhoneNumber phoneNumber,
            String name,
            String description,
            Address address,
            Location location,
            String contactDetails,
            WorkHours workHours,
            List<AggregateId> amenities,
            boolean isActive) {
        return new Facility(id, userId, phoneNumber, name, description, address, location, contactDetails, workHours, amenities, isActive);
    }
}
