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
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from your React app
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private DonorService donorService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Donor>> getAllDonors(){
        return ResponseEntity.of(Optional.of(donorRepository.findAll()));
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

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<Donor> login(@RequestBody Donor loginDonor) {
        Optional<Donor> donor = donorRepository.findByEmail(loginDonor.getEmail());

        if (donor.isPresent()) {
            // Check if the password matches (you might want to hash the password)
            if (donor.get().getPassword().equals(loginDonor.getPassword())) {
                return ResponseEntity.ok(donor.get());  // Successful login
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);  // Incorrect password
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Email not found
        }
    }

    // HISTORY
    @GetMapping("/history/{donorId}")
    public ResponseEntity<List<BloodStock>> getDonorHistory(@PathVariable Long donorId){
        return ResponseEntity.status(HttpStatus.OK).body(donorService.getDonorHistory(donorId));
    }

}
