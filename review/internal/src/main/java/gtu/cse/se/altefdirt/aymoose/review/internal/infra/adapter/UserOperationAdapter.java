package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter;

import java.util.List;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.account.api.provider.AccountProvider;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.UserOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserOperationAdapter implements UserOperationPort {

    private final AccountProvider accountProvider;

    @Override
    public FullName getAuthor(AggregateId id) {
        return accountProvider.getFullNameById(id);
    }

    @Override
    public List<FullName> getAuthors(List<AggregateId> ids) {
        return accountProvider.getFullNamesByIds(ids);
    }
}