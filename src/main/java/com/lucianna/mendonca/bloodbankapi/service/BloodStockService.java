package com.lucianna.mendonca.bloodbankapi.service;

import com.lucianna.mendonca.bloodbankapi.model.BloodStock;
import com.lucianna.mendonca.bloodbankapi.repository.BloodStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BloodStockService {

    @Autowired
    private BloodStockRepository bloodStockRepository;

    public Boolean isBloodStockAvailable(String bloodGroup){
        List<BloodStock> availableStocks = getAllAvailableStocks();
        for(BloodStock stock : availableStocks){
            if (stock.getBloodGroup().equals(bloodGroup) ){
                return true;
            }
        }
        return false;
    }

    public List<BloodStock> getAllAvailableStocks(){
        List<BloodStock> stocks = bloodStockRepository.findAll();
        List<BloodStock> availableStocks = new ArrayList<>();

        for(BloodStock stock : stocks){
            if (stock.getStatus().equals("Available") ){
                availableStocks.add(stock);
            }
        }
        return availableStocks;
    }
}
