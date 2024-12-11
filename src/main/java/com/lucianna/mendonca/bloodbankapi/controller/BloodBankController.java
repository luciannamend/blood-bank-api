package com.lucianna.mendonca.bloodbankapi.controller;

import com.lucianna.mendonca.bloodbankapi.model.BloodBank;
import com.lucianna.mendonca.bloodbankapi.repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blood-bank") //base url
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from web app
public class BloodBankController {

    @Autowired
    private BloodBankRepository bloodBankRepository;

    // GET ALL BLOOD BANKS
    @GetMapping
    public ResponseEntity<List<BloodBank>> getAllBanks(){
        return ResponseEntity.of(Optional.of(bloodBankRepository.findAll()));
    }

    // GET BLOOD BANK BY ID
    @GetMapping("/{bloodBankId}")
    public ResponseEntity<BloodBank> getBloodBank(@PathVariable Long bloodBankId){
        if(bloodBankId == null){
            System.out.println("BloodBank Id is null");
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.of(bloodBankRepository.findById(bloodBankId));
    }

    // CREATE NEW BLOOD BANK
    @PostMapping()
    public ResponseEntity<BloodBank> createBloodBank(@RequestBody BloodBank bloodBank){
        if(bloodBank == null){
            System.out.println("BloodBank is null");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bloodBankRepository.save(bloodBank));
    }

    // UPDATE BLOOD BANK BY ID
    @PutMapping("/{bloodBankId}")
    public ResponseEntity<BloodBank> updateBloodBank(@PathVariable Long bloodBankId, @RequestBody BloodBank bloodBank){
        if(bloodBankId == null){
            System.out.println("BloodBank Id is null");
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bloodBankRepository.save(bloodBank));
    }

    // DELETE BLOOD BANK BY ID
    @DeleteMapping("/{bloodBankId}")
    public ResponseEntity<BloodBank> deleteBloodBank(@PathVariable Long bloodBankId){

        if(bloodBankId == null){
            System.out.println("bloodBank Id is null");
            return ResponseEntity.notFound().build(); // 404
        }

        Optional<BloodBank> optionalBloodBank = bloodBankRepository.findById(bloodBankId);
        BloodBank bloodBank = optionalBloodBank.get();
        bloodBankRepository.delete(bloodBank);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
