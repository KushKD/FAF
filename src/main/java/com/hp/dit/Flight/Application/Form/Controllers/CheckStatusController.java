package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import com.hp.dit.Flight.Application.Form.form.CheckStatusForm;
import com.hp.dit.Flight.Application.Form.form.RegisterUser;
import com.hp.dit.Flight.Application.Form.form.ViewApplications;
import com.hp.dit.Flight.Application.Form.projections.CheckStatusProjection;
import com.hp.dit.Flight.Application.Form.projections.FormDataListProjection;
import com.hp.dit.Flight.Application.Form.services.FlightFormService;
import com.hp.dit.Flight.Application.Form.utilities.Constants;
import com.hp.dit.Flight.Application.Form.utilities.GeneratePdfReport;
import com.hp.dit.Flight.Application.Form.validators.CheckStatusValidator;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.CheckedInputStream;

@Controller
public class CheckStatusController {

    @Autowired
    private CheckStatusValidator checkStatusValidator;

    @Autowired
    private FlightFormService flightFormService;

    private static final Logger logger = LoggerFactory.getLogger(CheckStatusController.class);

    @RequestMapping(value = "/checkStatus", method = RequestMethod.GET)
    public String checkStatus(Model model) {
        model.addAttribute("checkStatus", new CheckStatusForm());
        return "checkStatus";
    }

    //checkStatusApp
    @RequestMapping(value = "/checkStatusApp", method = RequestMethod.POST)
    public String checkStatusApp(@ModelAttribute("checkStatus") CheckStatusForm applicationId, BindingResult bindingResult, Model model, HttpServletRequest request) {
        checkStatusValidator.validate(applicationId, bindingResult);

        if (bindingResult.hasErrors()) {
            return "checkStatus";
        }

        try {
            List<Object[]> data = null;
           data = flightFormService.checkApplicationStatus(Integer.parseInt(applicationId.getApplicationId()));

            if (!data.isEmpty()) {

                List<CheckStatusProjection> projectionData = new ArrayList<>();


                for (Object[] result : data) {
                    CheckStatusProjection pojo = new CheckStatusProjection();
                    pojo.setApplicationStatus((String)result[0]);
                    pojo.setComments((String) result[1]);
                    pojo.setUserId((Integer) result[2]);
                    projectionData.add(pojo);
                }



                request.getSession().setAttribute("successMessage", "Data found Successfully");
                model.addAttribute("applicationData", projectionData);
                return "checkStatus";
            } else {

                model.addAttribute("serverError", "No Data available for the current District and Barrier");
                return "checkStatus";
            }


        } catch (Exception ex) {
            model.addAttribute("serverError", ex.toString());
            return "checkStatus";
        }



    }


    //generatePdf
    @RequestMapping(value = "/generatePdf/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    ResponseEntity<InputStreamResource> printId(@PathVariable("id") String id) throws IOException, WriterException, DocumentException {

        FlightFormEntity vehicleOwnerEntries = flightFormService.getDataByUserID(Integer.parseInt(id));
        ByteArrayInputStream bis = GeneratePdfReport.generateIdCard(vehicleOwnerEntries);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + vehicleOwnerEntries.getFullName() + ".pdf");


        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));

    }


}
