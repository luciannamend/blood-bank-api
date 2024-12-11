package com.lucianna.mendonca.bloodbankapi.controller;

import com.lucianna.mendonca.bloodbankapi.model.BloodStock;
import com.lucianna.mendonca.bloodbankapi.repository.BloodStockRepository;
import com.lucianna.mendonca.bloodbankapi.service.BloodStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blood-stock")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from web app
public class BloodStockController {

    @Autowired
    private BloodStockRepository bloodStockRepository;
    BloodStockService stockService;

    // GET ALL BLOOD STOCKS
    @GetMapping
    public ResponseEntity<List<BloodStock>> getAllBloodStocks(){
        return ResponseEntity.of(Optional.of(bloodStockRepository.findAll()));
    }

    // GET BLOOD STOCK BY ID
    @GetMapping("/{bloodStockId}")
    public ResponseEntity<BloodStock> getBloodStock(@PathVariable Long bloodStockId){
        if(bloodStockId == null){
            System.out.println("BloodStock Id is null");
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.of(bloodStockRepository.findById(bloodStockId));
    }

    // CREATE NEW BLOOD STOCK
    @PostMapping()
    public ResponseEntity<BloodStock> createBloodStock(@RequestBody BloodStock bloodStock){
        if(bloodStock == null){
            System.out.println("BloodStock is null");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bloodStockRepository.save(bloodStock));
    }

    // UPDATE BLOOD STOCK BY ID
    @PutMapping("/{bloodStockId}")
    public ResponseEntity<BloodStock> updateBloodStock(@PathVariable Long bloodStockId,
                                                       @RequestBody BloodStock bloodStock){
        if(bloodStockId == null){
            System.out.println("BloodStock Id is null");
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bloodStockRepository.save(bloodStock));
    }

    // DELETE BLOOD STOCK BY ID
    @DeleteMapping("/{bloodStockId}")
    public ResponseEntity<BloodStock> deleteBloodStock(@PathVariable Long bloodStockId){

        if(bloodStockId == null){
            System.out.println("BloodStock Id is null");
            return ResponseEntity.notFound().build(); // 404
        }

        Optional<BloodStock> optionalBloodStock = bloodStockRepository.findById(bloodStockId);
        BloodStock bloodStock = optionalBloodStock.get();
        bloodStockRepository.delete(bloodStock);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
