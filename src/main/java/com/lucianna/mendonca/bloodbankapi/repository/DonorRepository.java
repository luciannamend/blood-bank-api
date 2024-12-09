package com.lucianna.mendonca.bloodbankapi.repository;

import com.lucianna.mendonca.bloodbankapi.model.Donor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    Optional<Donor> findByEmail(@NotBlank(message = "Email cannot be blank") @Email(message = "Email format is invalid") String email);
}
