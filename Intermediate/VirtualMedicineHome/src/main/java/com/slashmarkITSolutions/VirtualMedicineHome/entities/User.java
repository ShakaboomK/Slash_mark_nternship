package com.slashmarkITSolutions.VirtualMedicineHome.entities;


import com.slashmarkITSolutions.VirtualMedicineHome.utility.Address;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.*;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @NotNull
    private String userName;
    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Address address;
    @NotNull
    private String contactNumber;

    private String licenseNumber;

    private String specialization;

    



}

