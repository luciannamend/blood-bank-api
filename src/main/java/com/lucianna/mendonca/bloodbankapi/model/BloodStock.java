package com.lucianna.mendonca.bloodbankapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "blood_stock")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BloodStock {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blood_stock_id")
    private Long bloodStockId;

    // DONOR
    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor; // A BloodStock belongs to one Donor

    // BLOOD BANK DESTINATION
    @ManyToOne
    @JoinColumn(name = "blood_bank_id", nullable = false)
    private BloodBank bloodBank; // A BloodStock belongs to one BloodBank

    // BLOOD GROUP
    @NotNull(message = "Blood group cannot be null")
    @Pattern(
            regexp = "^(A|B|AB|O)[+-]$",
            message = "Blood group must be a valid type (e.g., A+, O-, AB+)"
    )
    @Column(name = "blood_group", nullable = false, columnDefinition = "VARCHAR(10)", length = 10)
    private String bloodGroup;

    // QUANTITY
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // BEST BEFORE
    @NotNull(message = "Best before date cannot be null")
    @Future(message = "Best before date must be in the future")
    @Column(name = "best_before", nullable = false)
    private LocalDate bestBefore;

    // STATUS -> AVAILABLE | UNAVAILABLE | EXPIRED
    @NotNull(message = "Status cannot be null")
    @Size(max = 20, message = "Status must not exceed 20 characters")
    @Pattern(
            regexp = "^(Available|Unavailable|Expired)$",
            message = "Status must be one of the following: Available, Unavailable, Expired"
    )
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20)", length = 20)
    private String status;
}
