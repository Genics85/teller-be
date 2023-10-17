package com.teller.service;

import com.teller.model.Account;
import com.teller.model.dto.CreateAccountDto;
import com.teller.model.dto.UpdateAccountDto;

public interface AccountService {

    Account get (Long id);
    Account add (CreateAccountDto createAccountDto);

    Account update (UpdateAccountDto updateAccountDto);

    Account getByNumber(String accountNumber);

}
