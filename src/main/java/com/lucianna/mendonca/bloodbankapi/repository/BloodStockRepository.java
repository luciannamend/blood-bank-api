package com.lucianna.mendonca.bloodbankapi.repository;

import com.lucianna.mendonca.bloodbankapi.model.BloodStock;
import com.lucianna.mendonca.bloodbankapi.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodStockRepository extends JpaRepository<BloodStock, Long> {

    List<BloodStock> findByDonor(Donor donor);

}
