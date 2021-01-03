package com.hp.dit.Flight.Application.Form.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReport {


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);


    public static ByteArrayInputStream generateIdCard(FlightFormEntity data) throws JsonProcessingException {
        FlightFormEntity vehicleOwnerEntries = null;
        ObjectMapper objectMapper = new ObjectMapper();

        vehicleOwnerEntries = data;
        String postJson = objectMapper.writeValueAsString(vehicleOwnerEntries);
        Document document = new Document(PageSize.A4, 1, 1, 1, 1);
        document.addTitle(String.valueOf(vehicleOwnerEntries.getUserId()));


        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font boldFont2 = new Font(Font.FontFamily.TIMES_ROMAN, 10);

        try {

            PdfPTable parent = new PdfPTable(2);
            float[] columnWidthsnestedparent = {50f, 50f};
            parent.setWidths(columnWidthsnestedparent);
            parent.setWidthPercentage(100);


            // Create a Simple table
            PdfPTable one = new PdfPTable(2);
            float[] columnWidthsnested = {70f, 30f};
            one.setWidths(columnWidthsnested);
            one.getDefaultCell().setBorder(0);

            PdfPCell cell = new PdfPCell(new Phrase("Lahaul & Spiti District Administration", boldFont));
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            one.addCell(cell);


            cell = new PdfPCell(new Phrase("Flight Service Pass (Winter Season 2021)", boldFont));
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            one.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(vehicleOwnerEntries.getUserId()), boldFont));
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            one.addCell(cell);


            // Create a new Table
            PdfPTable childTable1 = new PdfPTable(2);
            float[] y = {40f, 60f};
            childTable1.setWidths(y);
            // childTable1.addCell(new Phrase("Reg No:", boldFont2));
            // childTable1.addCell(new Phrase(vehicleOwnerEntries.getIdCardNumber(), boldFont2));


            childTable1.addCell(new Phrase("Name:", boldFont2));
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getFullName(), boldFont2));
            childTable1.addCell(new Phrase("User Type:", boldFont2));
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getCategory().getUserTypeName(), boldFont2));
            childTable1.addCell(new Phrase("Reservation Type:", boldFont2));
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getRegistrationType().getReservationTypeName(), boldFont2));
            childTable1.addCell(new Phrase("Age:", boldFont2));
            childTable1.addCell(new Phrase(String.valueOf(vehicleOwnerEntries.getAge()), boldFont2));
            childTable1.addCell(new Phrase("Weight:", boldFont2));
            childTable1.addCell(new Phrase(String.valueOf(vehicleOwnerEntries.getWeight()), boldFont2));
            childTable1.addCell(new Phrase("Luggage Weight:", boldFont2));
            childTable1.addCell(new Phrase(String.valueOf(vehicleOwnerEntries.getLuggageWeight()) + "KG", boldFont2));
            childTable1.addCell(new Phrase("Mobile Number:", boldFont2));
            childTable1.addCell(new Phrase(String.valueOf(vehicleOwnerEntries.getMobileNumber()), boldFont2));
            childTable1.addCell(new Phrase("Relation Name:", boldFont2));
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getRelationPrifix().getRelationshipPrifixName()+"   "+vehicleOwnerEntries.getRelationName(), boldFont2));


//            Image image = Image.getInstance(new URL(Utilities.getPhotoUrl(vehicleOwnerEntries.getVehicleOwnerImageName())));
//            PdfPTable childTable2 = new PdfPTable(1);
//            childTable2.addCell(image);

            one.addCell(childTable1);
        //    one.addCell(childTable2);

            //One Ends

            //Two
            // Create a Simple table
            PdfPTable two = new PdfPTable(2);
            float[] columnWidthsnestedtwo = {70f, 30f};
            two.setWidths(columnWidthsnestedtwo);
            two.getDefaultCell().setBorder(0);


            // Create a new Table
            PdfPTable childTable1two = new PdfPTable(2);
            float[] x = {40f, 60f};
            childTable1two.setWidths(x);
            childTable1two.addCell(new Phrase("Permanent Address:", boldFont2));
            childTable1two.addCell(new Phrase(vehicleOwnerEntries.getPermanentAddress(), boldFont2));
            childTable1two.addCell(new Phrase("Correspondence Address:", boldFont2));
            childTable1two.addCell(new Phrase(vehicleOwnerEntries.getCorrespondenceAddress(), boldFont2));
            childTable1two.addCell(new Phrase("From Location:", boldFont2));
            childTable1two.addCell(new Phrase(vehicleOwnerEntries.getFlightDistrictToGoFrom().getDistrictName(), boldFont2));
            childTable1two.addCell(new Phrase("From Helipad:", boldFont2));
            childTable1two.addCell(new Phrase(vehicleOwnerEntries.getFlightHelipadNameToGoFrom().getHelipadName(), boldFont2));
            childTable1two.addCell(new Phrase("Tentitive Flight Date:", boldFont2));
            childTable1two.addCell(new Phrase(vehicleOwnerEntries.getTentitiveFlightDate(), boldFont2));

            JsonObject jsonObjecttwo = new JsonObject();
            jsonObjecttwo.addProperty("full_name", vehicleOwnerEntries.getFullName());
            jsonObjecttwo.addProperty("mobile_number", vehicleOwnerEntries.getMobileNumber());
            jsonObjecttwo.addProperty("user_id", vehicleOwnerEntries.getUserId());

            //postJson
            BarcodeQRCode barcodeQRCodetwo = new BarcodeQRCode(jsonObjecttwo.toString(), 50, 50, null);
            Image codeQrImagetwo = barcodeQRCodetwo.getImage();
            PdfPTable childTable2two = new PdfPTable(1);
            childTable2two.addCell(codeQrImagetwo);

            two.addCell(childTable1two);
            two.addCell(childTable2two);
            //Two Ends

            cell = new PdfPCell(new Phrase("If found Please handover to DC Office Police", boldFont));
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            two.addCell(cell);

            parent.addCell(one);
            parent.addCell(two);


            PdfWriter.getInstance(document, out);
            document.open();

            document.add(parent);

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}