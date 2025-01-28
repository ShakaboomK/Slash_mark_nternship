package com.slashmarkITSolutions.VirtualMedicineHome.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Doctors")
public class Doctor extends User{

    

}
