package com.teller.controller;

import com.teller.model.Teller;
import com.teller.model.dto.CreateTellerDto;
import com.teller.model.dto.TellerLoginDto;
import com.teller.service.TellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teller")
public class TellerController {

    TellerService tellerService;


    public TellerController(TellerService tellerService) {
        this.tellerService = tellerService;
    }

    @PostMapping
    public ResponseEntity<Teller> create(@RequestBody CreateTellerDto createTellerDto){
        return new ResponseEntity<>(tellerService.add(createTellerDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Teller> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(tellerService.get(id),HttpStatus.FOUND);
    }
    @PostMapping("/login")
    public ResponseEntity<Teller> login(@RequestBody TellerLoginDto tellerLoginDto){
        return new ResponseEntity<>(tellerService.login(tellerLoginDto),HttpStatus.OK);
    }
}
