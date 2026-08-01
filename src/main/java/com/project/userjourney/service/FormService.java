package com.project.userjourney.service;

import com.project.userjourney.dto.*;
import com.project.userjourney.model.Customer;
import com.project.userjourney.model.enumeration.Area;
import com.project.userjourney.model.enumeration.Coverage;
import com.project.userjourney.model.enumeration.ValidationStatus;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FormService {
    public FormDataResponse getFormData() {
        List<FormDataResponse.AreaOfTravel> areaOfTravelOption = new ArrayList<>();
        List<FormDataResponse.Coverage> coverageOption = new ArrayList<>();

        for(Area area : Area.values()) {
            FormDataResponse.AreaOfTravel data = new FormDataResponse.AreaOfTravel();
            data.setName(area.getValue());
            data.setDescription(area.getDescription());
            data.setCode(area.toString());

            areaOfTravelOption.add(data);
        }

        for(Coverage coverage : Coverage.values()) {
            FormDataResponse.Coverage data = new FormDataResponse.Coverage();
            data.setName(coverage.getValue());
            data.setCode(coverage.toString());

            coverageOption.add(data);
        }

        return new FormDataResponse(areaOfTravelOption, coverageOption);
    }

    public PlanDetailValidationResponse planDetailValidation(PlanDetailRequest planDetailRequest) {
        PlanDetailValidationResponse planDetailValidationResponse = new PlanDetailValidationResponse();
        PlanDetailValidationResponse.CriteriaObject criteriaObject = new PlanDetailValidationResponse.CriteriaObject();
        PlanDetailValidationResponse.Dates dates = new PlanDetailValidationResponse.Dates();

        String chkArea = checkArea(planDetailRequest.getArea().toUpperCase(), planDetailRequest.getCoverage().toUpperCase());
        if(!chkArea.isEmpty()) {
            criteriaObject.setArea(chkArea);
        }

        String chkStartDate = checkStartDate(planDetailRequest.getDates().getStartDate());
        if(!chkStartDate.isEmpty()) {
            dates.setStartDate(chkStartDate);
        }

        String chkEndDate = checkEndDate(planDetailRequest.getDates().getStartDate(), planDetailRequest.getDates().getEndDate(), planDetailRequest.getCoverage());
        if(!chkEndDate.isEmpty()) {
            dates.setEndDate(chkEndDate);
        }
        criteriaObject.setDates(dates);
        planDetailValidationResponse.setStatus(getValidationStatus(criteriaObject));
        planDetailValidationResponse.setCriteria(criteriaObject);

        return planDetailValidationResponse;
    }

    private String checkArea(String areaOfTravel, String coverage) {
        String message = "AREA 4 is not available for ANNUAL coverage";
        Coverage coverageEnum = Coverage.fromValue(coverage);
        Area areaEnum = Area.fromValue(areaOfTravel);

        return areaEnum.equals(Area.AREA_4) && coverageEnum.equals(Coverage.ANNUAL) ? message: "";

    }

    private String checkStartDate(LocalDate startDate) {
        String message_minimumValue = "minimum start date Today";
        String message_maximumValue = "maximum start date is one(1) year after today";
        if(startDate.isBefore(LocalDate.now())){
            return message_minimumValue;
        }
        else if(startDate.isAfter(LocalDate.now().plusYears(1))){
            return message_maximumValue;
        }
        return "";
    }

    private String checkEndDate(LocalDate startDate, LocalDate endDate, String coverage) {
        Coverage coverageEnum = Coverage.fromValue(coverage);
        if(coverageEnum.equals(Coverage.SINGLE)){
            if(endDate.isAfter(startDate.plusDays(180))){
                return "For Single coverage,End Date cannot exceed 180 days from Start Date";
            }
            else if(endDate.isBefore(startDate)){
                return "End Date cannot be before Start Date";
            }
        }
        else{
            if(endDate.isAfter(startDate.plusYears(1).minusDays(1))){
                return "End Date should only be 1 year after Start Date";
            }
        }

        return "";
    }

    private String getValidationStatus(PlanDetailValidationResponse.CriteriaObject criteriaObject) {
        if(criteriaObject.getCoverage().isEmpty() && criteriaObject.getArea().isEmpty() && criteriaObject.getDates().getStartDate().isEmpty() && criteriaObject.getDates().getEndDate().isEmpty()) {
            return ValidationStatus.SUCCESS.getValue();
        }

        return ValidationStatus.FAILED.getValue();
    }

    public CustomerDetailValidationResponse customerDetailValidation(CustomerDetailRequest customerDetailRequest) {
        CustomerDetailValidationResponse customerDetailValidationResponse = new CustomerDetailValidationResponse();
        CustomerDetailValidationResponse.CriteriaObject criteriaObject = new CustomerDetailValidationResponse.CriteriaObject();

        String chkFullName = checkFullName(customerDetailRequest.getFullName());
        if(!chkFullName.isEmpty()) {
            criteriaObject.setFullName(chkFullName);
        }

        String chkEmail = checkEmail(customerDetailRequest.getEmail());
        if(!chkEmail.isEmpty()) {
            criteriaObject.setEmail(chkEmail);
        }

        String chkNric = checkNric(customerDetailRequest.getNric());
        if(!chkNric.isEmpty()) {
            criteriaObject.setNric(chkNric);
        }

        String chkPhone = checkPhone(customerDetailRequest.getMobileNo());
        if(!chkPhone.isEmpty()) {
            criteriaObject.setMobileNo(chkPhone);
        }

        String chkAddress = checkAddress(customerDetailRequest.getAddressLine1());
        if(!chkAddress.isEmpty()) {
            criteriaObject.setAddressLine1(chkAddress);
        }

        String chkPostCode = checkPostCode(customerDetailRequest.getPostCode());
        if(!chkPostCode.isEmpty()) {
            criteriaObject.setPostCode(chkPostCode);
        }

        customerDetailValidationResponse.setStatus(getCustomerDetailValidationStatus(criteriaObject));
        customerDetailValidationResponse.setCriteria(criteriaObject);

        return customerDetailValidationResponse;
    }

    private String checkFullName(String fullName) {
        String message = checkRequired(fullName);
        if(!message.isEmpty()) {
            return message;
        }
        return "";
    }

    private String checkNric(String nric) {
        String message = checkRequired(nric);
        if(!message.isEmpty()) {
            return message;
        }

        if (!nric.matches("\\d{12}")) {
            return "Identity number must contain exactly 12 digits";
        }

        // Validate first 6 digits as YYMMDD
        String datePart = nric.substring(0, 6);

        int year = Integer.parseInt(datePart.substring(0, 2));
        int month = Integer.parseInt(datePart.substring(2, 4));
        int day = Integer.parseInt(datePart.substring(4, 6));

        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            return "the first 6 digits in nric is not a valid date";
        }

        return "";
    }

    private String checkEmail(String email) {
        String message = checkRequired(email);
        if(!message.isEmpty()) {
            return message;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            return "Invalid email address";
        }

        return "";
    }

    private String checkPhone(String phone) {
        String message = checkRequired(phone);
        if(!message.isEmpty()) {
            return message;
        }
        if (!phone.matches("01\\d{7,9}")) {
            return "Mobile number must be 9 to 11 digits and start with 01";
        }

        return "";
    }

    private String checkAddress(String address) {
        String message = checkRequired(address);
        if(!message.isEmpty()) {
            return message;
        }
        return "";
    }

    private String checkPostCode(String postCode) {
        String message = checkRequired(postCode);
        if(!message.isEmpty()) {
            return message;
        }
        if (!postCode.matches("\\d{5}")) {
            return "postCode must contain exactly 5 digits";
        }
        return "";
    }

    private String checkRequired(String value) {
        return value.isEmpty() ? "This field is required" : "";
    }

    private String getCustomerDetailValidationStatus(CustomerDetailValidationResponse.CriteriaObject criteriaObject) {
        if(criteriaObject.getFullName().isEmpty()
                && criteriaObject.getNric().isEmpty()
                && criteriaObject.getEmail().isEmpty()
                && criteriaObject.getMobileNo().isEmpty()
                && criteriaObject.getAddressLine1().isEmpty()
                && criteriaObject.getPostCode().isEmpty()) {
            return ValidationStatus.SUCCESS.getValue();
        }

        return ValidationStatus.FAILED.getValue();
    }

    public Boolean getSubmissionValidationStatus(SubmissionRequest submissionRequest) {
        CustomerDetailRequest customerDetailRequest = getCustomerDetailRequest(submissionRequest);
        CustomerDetailValidationResponse customerDetailValidationResponse = customerDetailValidation(customerDetailRequest);

        PlanDetailRequest planDetailRequest = new PlanDetailRequest();
        planDetailRequest.setArea(submissionRequest.getArea());
        planDetailRequest.setCoverage(submissionRequest.getCoverage());
        planDetailRequest.setDates(new PlanDetailRequest.Dates(submissionRequest.getStartDate(),submissionRequest.getEndDate()));

        PlanDetailValidationResponse planDetailValidationResponse = planDetailValidation(planDetailRequest);

        return Objects.equals(planDetailValidationResponse.getStatus(), ValidationStatus.SUCCESS.getValue())
                && Objects.equals(customerDetailValidationResponse.getStatus(), ValidationStatus.SUCCESS.getValue());
    }

    private static @NonNull CustomerDetailRequest getCustomerDetailRequest(SubmissionRequest submissionRequest) {
        SubmissionRequest.CustomerDetail customer =  submissionRequest.getCustomer();

        CustomerDetailRequest customerDetailRequest = new CustomerDetailRequest();
        customerDetailRequest.setFullName(customer.getFullName());
        customerDetailRequest.setNric(customer.getNric());
        customerDetailRequest.setEmail(customer.getEmail());
        customerDetailRequest.setMobileNo(customer.getMobileNo());
        customerDetailRequest.setAddressLine1(customer.getAddressLine1());
        customerDetailRequest.setPostCode(customer.getPostCode());

        return customerDetailRequest;
    }
}
