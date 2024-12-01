package gtu.cse.se.altefdirt.aymoose.account.internal.application.service;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;

public interface AccountService {
    
    AccountView denormalize(Account account);

    String randomProfilePicture();
}
