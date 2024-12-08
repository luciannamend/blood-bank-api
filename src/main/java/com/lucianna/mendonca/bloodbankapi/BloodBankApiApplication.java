package com.lucianna.mendonca.bloodbankapi;

import com.lucianna.mendonca.bloodbankapi.model.BloodStock;
import com.lucianna.mendonca.bloodbankapi.service.BloodStockService;
import com.lucianna.mendonca.bloodbankapi.service.DonorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BloodBankApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloodBankApiApplication.class, args);

    }

    @Bean
    public CommandLineRunner testBloodStock(BloodStockService bloodStockService, DonorService donorService) {
        return args -> {

            // Test the isBloodStockAvailable()
            String bloodGroup = "O+";
            System.out.println("Is blood stock available for " + bloodGroup + ": " + bloodStockService.isBloodStockAvailable(bloodGroup));

            // Test th getAllAvailableStocks()
            System.out.println("All available stocks:");
            for (BloodStock stock : bloodStockService.getAllAvailableStocks()){
                System.out.println(stock.toString());
            }

            // test the getDonorHistory()
            Long donorId = 2L;
            List<BloodStock> donorsStocks = donorService.getDonorHistory(donorId);
            System.out.println("Donor id: "+ donorId + "\nAvailable stocks:");
            for (BloodStock stock:donorsStocks){
                System.out.println(stock.toString());

            }
        };
    }

}
