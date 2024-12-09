package com.lucianna.mendonca.bloodbankapi.service;

import com.lucianna.mendonca.bloodbankapi.model.BloodStock;
import com.lucianna.mendonca.bloodbankapi.model.Donor;
import com.lucianna.mendonca.bloodbankapi.repository.BloodStockRepository;
import com.lucianna.mendonca.bloodbankapi.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private BloodStockRepository bloodStockRepository;

    public List<BloodStock> getDonorHistory(Long donorId) {
        List<BloodStock> stocks = bloodStockRepository.findAll();
        List<BloodStock> donorsStocks = new ArrayList<>();

        for(BloodStock stock : stocks){

            if (donorId != null && donorId.equals(stock.getDonorId())) {
                donorsStocks.add(stock);
            }
        }
        return donorsStocks;
    }
}
