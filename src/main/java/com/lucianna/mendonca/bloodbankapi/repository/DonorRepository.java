package com.lucianna.mendonca.bloodbankapi.repository;

import com.lucianna.mendonca.bloodbankapi.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
}
