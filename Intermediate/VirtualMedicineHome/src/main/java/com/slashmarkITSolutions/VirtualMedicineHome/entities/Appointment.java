package com.slashmarkITSolutions.VirtualMedicineHome.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "appointments")
@Data
@Builder@NoArgsConstructor@AllArgsConstructor
public class Appointment {
    @Id
    private ObjectId id;

    private String doctorId;

    private String patientId;

    private LocalDateTime appointmentDate;

    private String appointmentStatus;

    private String notes;
    private LocalDateTime createdAt;
}
