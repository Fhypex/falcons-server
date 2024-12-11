package gtu.cse.se.altefdirt.aymoose.account.internal.infra.provider;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.account.api.provider.AccountProvider;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

@Component
class AccountProviderImpl implements AccountProvider {

    @Override
    public FullName getFullNameById(AggregateId id) {
        return new FullName("John", "Doe");
    }

    @Override
    public List<FullName> getFullNamesByIds(List<AggregateId> ids) {
        return ids.stream().map(id -> new FullName("John", "Doe")).collect(Collectors.toList());
    }
}
