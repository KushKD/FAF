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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReport {


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);


    public static ByteArrayInputStream generateIdCard(FlightFormEntity data) throws JsonProcessingException {
        FlightFormEntity vehicleOwnerEntries = null;
        ObjectMapper objectMapper = new ObjectMapper();

        vehicleOwnerEntries = data;
        String postJson = objectMapper.writeValueAsString(vehicleOwnerEntries);
        Document document = new Document(PageSize.A4, 40 , 40, 40, 40);
        document.addTitle(String.valueOf(vehicleOwnerEntries.getUserId()));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font boldFont2 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        Font boldFontIns = new Font(Font.FontFamily.TIMES_ROMAN, 10);

        try {

            PdfPTable parent = new PdfPTable(1);
            float[] columnWidthsnestedparent = {100f};
            parent.setWidths(columnWidthsnestedparent);
            parent.setWidthPercentage(100);

            //Zero
            PdfPTable zero = new PdfPTable(1);
            float[] columnWidthsnestedz = {100f};
            zero.setWidths(columnWidthsnestedz);
            zero.getDefaultCell().setBorder(0);

            // Create a new Table
            PdfPTable childTable0 = new PdfPTable(6);
            float[] z = {25f,25f, 25f,25f,25f,25f};
            childTable0.setWidths(z);
            childTable0.getDefaultCell().setBorder(0);

            Image image = Image.getInstance(new URL(Utilities.getPhotoUrl("hp_logo.png")));
           // image.setUseVariableBorders(false);
            image.setBorder(Image.NO_BORDER);
            childTable0.addCell(image);

            PdfPCell cellheader = new PdfPCell(new Phrase("Flight Service Pass (Lahaul & Spiti for Winter Season 2021)", boldFont));
            cellheader.setColspan(4);
            cellheader.setBorder(0);
            cellheader.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellheader.setHorizontalAlignment(Element.ALIGN_CENTER);
            childTable0.addCell(cellheader);



            JsonObject jsonObjecttwo = new JsonObject();
            jsonObjecttwo.addProperty("full_name", vehicleOwnerEntries.getFullName());
            jsonObjecttwo.addProperty("mobile_number", vehicleOwnerEntries.getMobileNumber());
            jsonObjecttwo.addProperty("user_id", vehicleOwnerEntries.getUserId());

            //postJson
            BarcodeQRCode barcodeQRCodetwo = new BarcodeQRCode(jsonObjecttwo.toString(), 60, 60, null);
            Image codeQrImagetwo = barcodeQRCodetwo.getImage();
            childTable0.addCell(codeQrImagetwo);

            zero.addCell(childTable0);


            // Create a Simple table
            PdfPTable one = new PdfPTable(1);
            float[] columnWidthsnested = {100f};
            one.setWidths(columnWidthsnested);
            one.getDefaultCell().setBorder(0);

            PdfPCell cell = new PdfPCell(new Phrase(vehicleOwnerEntries.getFullName(), boldFont));
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPadding(5);
            one.addCell(cell);


            // Create a new Table
            PdfPTable childTable1 = new PdfPTable(2);
            float[] y = {50f,50f};
            childTable1.setWidths(y);



            childTable1.addCell(getCell("Application ID:",boldFont2));
            childTable1.addCell(getCell( String.valueOf(vehicleOwnerEntries.getUserId()),boldFont2));
            childTable1.addCell(getCell("User Type:",boldFont2));
            childTable1.addCell(getCell( vehicleOwnerEntries.getCategory().getUserTypeName(),boldFont2));
            childTable1.addCell(getCell("Reservation Type:",boldFont2));
            childTable1.addCell(getCell( vehicleOwnerEntries.getRegistrationType().getReservationTypeName(),boldFont2));
            childTable1.addCell(getCell("Age:",boldFont2));
            childTable1.addCell(getCell( String.valueOf(vehicleOwnerEntries.getAge()),boldFont2));
            childTable1.addCell(getCell("Weight:",boldFont2));
            childTable1.addCell(getCell( String.valueOf(vehicleOwnerEntries.getWeight())+" KG",boldFont2));
            childTable1.addCell(getCell("Luggage Weight:",boldFont2));
            childTable1.addCell(getCell( String.valueOf(vehicleOwnerEntries.getLuggageWeight()),boldFont2));
            childTable1.addCell(getCell("Mobile Number:",boldFont2));
            childTable1.addCell(getCell( String.valueOf(vehicleOwnerEntries.getMobileNumber()),boldFont2));
            childTable1.addCell(getCell("Relation Name:",boldFont2));
            childTable1.addCell(getCell( vehicleOwnerEntries.getRelationPrifix().getRelationshipPrifixName()+"   "+ vehicleOwnerEntries.getRelationName(),boldFont2));
            childTable1.addCell(getCell("Permanent Address:",boldFont2));
            childTable1.addCell(getCell( vehicleOwnerEntries.getPermanentAddress(),boldFont2));
            childTable1.addCell(getCell("Correspondence Address:",boldFont2));
            childTable1.addCell(getCell( vehicleOwnerEntries.getCorrespondenceAddress(),boldFont2));
            childTable1.addCell(getCell("From Location:",boldFont2));
            childTable1.addCell(getCell( vehicleOwnerEntries.getFlightDistrictToGoFrom().getDistrictName(),boldFont2));
            childTable1.addCell(getCell("From Helipad:",boldFont2));
            childTable1.addCell(getCell( vehicleOwnerEntries.getFlightHelipadNameToGoFrom().getHelipadName(),boldFont2));
            childTable1.addCell(getCell("Tentitive Flying Date:",boldFont2));
            childTable1.addCell(getCell( vehicleOwnerEntries.getTentitiveFlightDate(),boldFont2));



            one.addCell(childTable1);



            //Two Ends


            one.addCell(instructionCell("Instruction1",boldFontIns));
            one.addCell(instructionCell("Instruction2",boldFontIns));
            one.addCell(instructionCell("Instruction3",boldFontIns));
            one.addCell(instructionCell("Instruction4",boldFontIns));
            one.addCell(instructionCell("Instruction5",boldFontIns));

            parent.addCell(zero);
            parent.addCell(one);


            PdfWriter.getInstance(document, out);
            document.open();

            document.add(parent);

            document.close();

        } catch (DocumentException | MalformedURLException ex) {

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static PdfPCell getCell(String data, Font font){

        PdfPCell cell = new PdfPCell(new Phrase(data, font));
        cell.setBorderColorLeft(new BaseColor(242,242,242));
        cell.setBorderColorRight(new BaseColor(242,242,242));
        cell.setBorderColorTop(new BaseColor(242,242,242));
        cell.setBorderColorBottom(new BaseColor(242,242,242));
        cell.setPadding(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        return cell;
    }

    private static PdfPCell instructionCell(String data, Font font){

        PdfPCell cell = new PdfPCell(new Phrase(data, font));
        cell.setColspan(2);
        cell.setBorderColorLeft(new BaseColor(242,242,242));
        cell.setBorderColorRight(new BaseColor(242,242,242));
        cell.setBorderColorTop(new BaseColor(242,242,242));
        cell.setBorderColorBottom(new BaseColor(242,242,242));
        cell.setPadding(5);
        cell.setBorder(0);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        return cell;
    }
}