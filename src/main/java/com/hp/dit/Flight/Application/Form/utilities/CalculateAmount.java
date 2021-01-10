package com.hp.dit.Flight.Application.Form.utilities;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;

public class CalculateAmount {

    public static String calculateAmount(FlightFormEntity user){
        String amountToDeposit = null;
        if(user.getCategory().getUserTypeName().equalsIgnoreCase("Patient") && user.getAge()>9){
            amountToDeposit = "750" ;//750/-
        }else if(user.getCategory().getUserTypeName().equalsIgnoreCase("Other") && user.getAge()>9){
            amountToDeposit = "7000";//7000/-
        }else if(user.getCategory().getUserTypeName().equalsIgnoreCase("Local") && user.getAge()>9){
            amountToDeposit = "1500";//1500/-
        }else if(user.getCategory().getUserTypeName().equalsIgnoreCase("Official") && user.getAge()>9){
            amountToDeposit = "1500";//1500/-
        }else if(user.getCategory().getUserTypeName().equalsIgnoreCase("Patient") && user.getAge()<=9){
            amountToDeposit = "750";//750/-
        }else if(user.getCategory().getUserTypeName().equalsIgnoreCase("Other") && user.getAge()<=9){
            amountToDeposit = "750";//750/-
        }else if(user.getCategory().getUserTypeName().equalsIgnoreCase("Local") && user.getAge()<=9){
            amountToDeposit = "750";//750/-
        }else{
            amountToDeposit = "0";
        }


        return amountToDeposit;
    }
}
