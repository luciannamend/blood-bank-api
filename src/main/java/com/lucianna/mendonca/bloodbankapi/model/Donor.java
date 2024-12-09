package com.lucianna.mendonca.bloodbankapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "donor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Donor {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donor_id")
    private Long donorId;

    //FIRST NAME
    @NotNull(message = "First name cannot be null")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(50)", length = 50)
    private String firstName;

    //LAST NAME
    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(50)", length = 50)
    private String lastName;

    //DOB
    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    //GENDER
    @NotNull(message = "Gender cannot be null")
    @Pattern(
            regexp = "^(Male|Female|Non-binary|Other)$",
            message = "Gender must be one of the following: Male, Female, Non-binary, Other"
    )
    @Column(name = "gender", nullable = false, columnDefinition = "VARCHAR(20)", length = 20)
    private String gender;

    //BlOOD GROUP
    @NotNull(message = "Blood group cannot be null")
    @Pattern(
            regexp = "^(A|B|AB|O)[+-]$",
            message = "Blood group must be a valid type (e.g., A+, O-, AB+)"
    )
    @Column(name = "blood_group", nullable = false, columnDefinition = "VARCHAR(10)", length = 10)
    private String bloodGroup;

    //CITY
    @NotBlank(message = "City cannot be blank")
    @Column(name = "city", nullable = false, columnDefinition = "VARCHAR(50)", length = 50)
    private String city;

    //PHONE NUMBER
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^\\+?[1-9][0-9 .()-]{6,19}$",
            message = "Phone number is invalid"
    ) // Matches common phone number formats
    @Column(name = "phone_number", nullable = false, columnDefinition = "VARCHAR(20)", length = 20)
    private String phoneNumber;

    // ------- LOGIN CREDENTIALS -------- //
    //EMAIL
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email format is invalid") //validates email format
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // PASSWORD
    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;
}
