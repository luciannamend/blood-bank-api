package com.lucianna.mendonca.bloodbankapi.controller;

import com.lucianna.mendonca.bloodbankapi.model.BloodStock;
import com.lucianna.mendonca.bloodbankapi.model.Donor;
import com.lucianna.mendonca.bloodbankapi.repository.DonorRepository;
import com.lucianna.mendonca.bloodbankapi.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/api/donors")
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private DonorService donorService;

    // GET ALL
//    @GetMapping
//    public ResponseEntity<List<Donor>> getAllDonors(){
//        return ResponseEntity.of(Optional.of(donorRepository.findAll()));
//    }

    // GET ALL
    @GetMapping
    public List<Donor> getAllDonors(){


        List<Donor> donors = donorRepository.findAll();

        for(Donor donor : donors){

            System.out.println("DONOR FOUND " + donor.getFirstName());
        }

        return donorRepository.findAll();
    }

    // GET BY ID
    @GetMapping("/{donorId}")
    public ResponseEntity<Donor> getDonor(@PathVariable Long donorId){
        if(donorId == null){
            System.out.println("Donor Id is null");
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.of(donorRepository.findById(donorId));
    }

    // GET HISTORY
    @GetMapping("/{donorId}/history")
    public List<BloodStock> getDonorHistory(@PathVariable Long donorId) {
        return donorService.getDonorHistory(donorId); // rtn all stocks stored with donor id
    }

    // CREATE
    @PostMapping()
    public ResponseEntity<Donor> createDonor(@RequestBody Donor donor){
        if(donor == null){
            System.out.println("Donor is null");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(donorRepository.save(donor));
    }

    // UPDATE
    @PutMapping("/{donorId}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long donorId, @RequestBody Donor donor){
        if(donorId == null){
            System.out.println("Donor Id is null");
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(donorRepository.save(donor));
    }

    // DELETE BY ID
    @DeleteMapping("/{donorId}")
    public ResponseEntity<Donor> deleteDonor(@PathVariable Long donorId){

        if(donorId == null){
            System.out.println("Donor Id is null");
            return ResponseEntity.notFound().build(); // 404
        }

        Optional<Donor> optionalDonor = donorRepository.findById(donorId);
        Donor donor = optionalDonor.get();
        donorRepository.delete(donor);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
