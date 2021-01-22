package com.hp.dit.Flight.Application.Form.utilities;

import com.hp.dit.Flight.Application.Form.form.FlightApplicationForm;

import java.util.regex.Pattern;

public class Constants {

    public static final String keyResponse = "RESPONSE";
    public static final String keyMessage = "MSG";
    public static final String keyStatus = "STATUS";
    public static final String valueMessage = "Request Successful.";
    public static final String valueMessageEmpty = "Please enter valid Vehicle Registration Number";


    /**
     * Credentials For the CDAC OTP
     */
    public static final String smsUsername = "hpgovt-covid";
    public static final String smsPassword = "Covid@12345";
    public static final String smsSenderId = "hpgovt";
    public static final String smsSecureKey = "f005c2a6-dcf3-4f24-886d-53a51c043be6";
    public static final String otp_Message = "Your OTP to login into the ID Card Generator Application is:  ";
    public static final String getCarDetailsVahan = "getDetails";
    public static final String clientId = "HPEDISTRICT";
    public static final String PENDING = "P";
    public static final String INCOMPLETE = "I";
    public static final String APPROVED = "A";
    public static final String REJECTED = "R";


    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }


    /**
     *
     * Merchant Name	DC L&S-HELICOPTER FLIGHT BOOKINGS
     * Key	7rnFly
     * Salt	pjVQAWpA
     * Merchant Domain	WWW.STAGING12.HP.GOV.IN/FLIGHT
     * SETUP TYPE	Hosted
     */

    /**
     * E Ticket PDF Instructions
     */

    public static String TICKET_HEADING = "E-FLIGHT TICKET";
    public static String INSTRUCTUIN_0 = "Instructions:";
    public static String INSTRUCTUIN_I = "The Luggage Should not exceed 10KG. ";
    public static String INSTRUCTUIN_II = "The amount charged shall be refunded if the booking is cancelled before 6 hours of scheduled flight.";
    public static String INSTRUCTUIN_III = "The passenger should reach at Helipad/Airport before the departure of Flight. ";
    public static String IMAGE_NAME_PASS = "hp_logo.png";



}
