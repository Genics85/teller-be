package com.teller.service;

import com.teller.model.Teller;
import com.teller.model.dto.CreateTellerDto;
import com.teller.model.dto.TellerLoginDto;

public interface TellerService {
    Teller add(CreateTellerDto createTellerDto);
    Teller get(Long id);

    Teller login(TellerLoginDto tellerLoginDto);
}
