package com.slashmarkITSolutions.VirtualMedicineHome.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "patients")
@Data
public class Patient extends User {
    private int age;
    private List<String> medicalHistoryIds;
    private String bloodGroup;
    private boolean donorStatus;
    private List<String> appointmentIds;


}
