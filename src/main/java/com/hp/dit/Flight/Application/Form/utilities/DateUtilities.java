package com.hp.dit.Flight.Application.Form.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtilities {

    public static String getDateTime()
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // mention your timezone
        return df.format(new Date());
    }

    public static String convertDate(String dateForm) throws ParseException {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        String inputDateStr=dateForm;
        Date date = inputFormat.parse(inputDateStr);
        String outputDateStr = outputFormat.format(date);
        return outputDateStr;
    }
}
