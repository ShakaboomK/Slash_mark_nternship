package com.slashmarkITSolutions.VirtualMedicineHome.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder@AllArgsConstructor
public class Address {

    private String doorNumber;

    private String streetOrColonyName;

    private String landMark;

    private String postalCode;

    private String cityOrTownOrVillage;

    private String state;

    private String country;

}
