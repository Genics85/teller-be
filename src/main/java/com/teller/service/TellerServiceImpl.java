package com.teller.service;

import com.teller.exception.ResourceNotFound;
import com.teller.model.Teller;
import com.teller.model.dto.CreateTellerDto;
import com.teller.model.dto.TellerLoginDto;
import com.teller.repository.TellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TellerServiceImpl implements TellerService {

    TellerRepo tellerRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public TellerServiceImpl(TellerRepo tellerRepo) {
        this.tellerRepo = tellerRepo;
    }


    @Override
    public Teller add(CreateTellerDto createTellerDto) {
        Teller newTeller = new Teller();

        newTeller.setEmail(createTellerDto.getEmail());
        newTeller.setFirstName(createTellerDto.getFirstName());
        newTeller.setLastName(createTellerDto.getLastName());
        newTeller.setPhoneNumber(createTellerDto.getPhoneNumber());
        newTeller.setPassword(passwordEncoder.encode(createTellerDto.getPassword()));

        return tellerRepo.save(newTeller);
    }

    @Override
    public Teller get(Long id) {

        return tellerRepo.findById(id).orElseThrow(()->new ResourceNotFound("Teller","id",id));
    }

    @Override
    public Teller login(TellerLoginDto tellerLoginDto) {
        Teller teller = tellerRepo.findByEmail(tellerLoginDto.getEmail());
        if(teller == null ) throw new ResourceNotFound("Teller","email",tellerLoginDto.getEmail());
        String encodedPassword = teller.getPassword();
        Boolean itMatches = passwordEncoder.matches(tellerLoginDto.getPassword(),encodedPassword);
        if(!itMatches) throw new ResourceNotFound("Teller","Password","***");

        return teller;
    }
}
