package com.lucianna.mendonca.bloodbankapi.repository;

import com.lucianna.mendonca.bloodbankapi.model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
}
