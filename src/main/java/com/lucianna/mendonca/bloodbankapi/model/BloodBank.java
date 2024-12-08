package com.lucianna.mendonca.bloodbankapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "blood_bank")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BloodBank {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blood_bank_id")
    private Long bloodBankId;

    // BLOOD BANK NAME
    @NotBlank(message = "Name cannot be blank") // Ensures non-null and non-empty
    @Size(max = 200, message = "Name cannot exceed 200 characters")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(200)", length = 200)
    private String name;

    // STREET ADDRESS
    @NotBlank(message = "Address cannot be blank")
    @Column(name = "address", nullable = false)
    private String address;

    // CITY
    @NotBlank(message = "City cannot be blank")
    @Column(name = "city", nullable = false, columnDefinition = "VARCHAR(50)", length = 50)
    private String city;

    // STATE
    @NotBlank(message = "State cannot be blank")
    @Column(name = "state", nullable = false, columnDefinition = "VARCHAR(50)", length = 50)
    private String state;

    // POSTAL CODE
    @NotBlank(message = "Postal Code cannot be blank")
    @Column(name = "postal_code", nullable = false, columnDefinition = "VARCHAR(50)", length = 50)
    private String postalCode;

    // PHONE NUMBER
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^\\+?[0-9. ()-]{7,20}$",
            message = "Phone number is invalid"
    ) // Matches common phone number formats
    @Column(name = "phone_number", nullable = false, columnDefinition = "VARCHAR(20)", length = 20)
    private String phoneNumber;

    // EMAIL
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email format is invalid") //validates email format
    @Column(name = "email", nullable = false)
    private String email;
}
