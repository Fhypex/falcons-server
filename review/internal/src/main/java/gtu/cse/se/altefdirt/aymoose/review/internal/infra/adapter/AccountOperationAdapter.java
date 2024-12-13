package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter;

import java.util.List;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.account.api.provider.AccountProvider;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.AccountOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.application.UserData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountOperationAdapter implements AccountOperationPort {

    private final AccountProvider accountProvider;

    @Override
    public UserData getAccount(AggregateId id) {
        return accountProvider.getAccountById(id);
    }

    @Override
    public List<UserData> getAccounts(List<AggregateId> ids) {
        return accountProvider.getAccountsByIds(ids);
    }
}