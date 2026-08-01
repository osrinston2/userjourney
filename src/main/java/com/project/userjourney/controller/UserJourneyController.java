package com.project.userjourney.controller;

import com.project.userjourney.dto.*;
import com.project.userjourney.exception.ResourceNotFoundException;
import com.project.userjourney.model.Price;
import com.project.userjourney.model.TravelInsurance;
import com.project.userjourney.model.enumeration.Area;
import com.project.userjourney.model.enumeration.Coverage;
import com.project.userjourney.service.CustomerService;
import com.project.userjourney.service.FormService;
import com.project.userjourney.service.PriceService;
import com.project.userjourney.service.TravelInsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("journey")
@Tag(name = "User Journey API", description = "APIs for managing user journey and travel insurance")
public class UserJourneyController {

    private final PriceService priceService;
    private final FormService formService;
    private final TravelInsuranceService travelInsuranceService;
    private final CustomerService customerService;

    @Autowired
    public UserJourneyController(PriceService priceService, FormService formService, TravelInsuranceService travelInsuranceService, CustomerService customerService) {
        this.priceService = priceService;
        this.formService = formService;
        this.travelInsuranceService = travelInsuranceService;
        this.customerService = customerService;
    }

    @GetMapping("load")
    @Operation(summary = "Load form data", description = "Retrieves all form data including areas, coverage types, and plans")
    public FormDataResponse loadFormData() {
        return formService.getFormData();
    }

    @GetMapping("price/{area}/{coverage}")
    @Operation(summary = "Find prices by area and coverage", description = "Retrieves prices based on the specified area and coverage type")
    public List<Price> findByAreaAndCoverage(
            @PathVariable @Parameter(description = "Area name (e.g AREA 1, AREA 2)") String area,
            @PathVariable @Parameter(description = "Coverage type (e.g., SINGLE TRIP, ANNUAL)") String coverage) {
        if (area == null || area.trim().isEmpty()) {
            throw new IllegalArgumentException("Area cannot be empty");
        }
        if (coverage == null || coverage.trim().isEmpty()) {
            throw new IllegalArgumentException("Coverage cannot be empty");
        }

        Coverage coverageEnum = Coverage.fromValue(coverage.toUpperCase());
        Area areaEnum = Area.fromValue(area.toUpperCase());

        if(areaEnum.equals(Area.AREA_4) && coverageEnum.equals(Coverage.ANNUAL)){
            throw new IllegalArgumentException("AREA 4 is not available for Coverage ANNUAL");
        }

        List<Price> prices = priceService.findByAreaAndCoverage(areaEnum.getValue(), coverageEnum.getValue());
        if (prices.isEmpty()) {
            throw new ResourceNotFoundException("No prices found for area: " + area + " and coverage: " + coverage);
        }
        return prices;
    }

    @PostMapping("insurance/submit")
    @Operation(summary = "Submit travel insurance", description = "Submits a travel insurance application with customer and plan details")
    public TravelInsurance travelInsuranceSubmission(@Valid @RequestBody SubmissionRequest submissionRequest) {
        Boolean validate = formService.getSubmissionValidationStatus(submissionRequest);

        if(!validate){
            throw new IllegalStateException("Validation failed: Please check your input via validation API");
        }

        TravelInsurance travelInsurance = new TravelInsurance();

        travelInsurance.setCustomerId(customerService.saveCustomer(submissionRequest).getCustomerId());
        travelInsurance.setCoverage(submissionRequest.getCoverage());
        travelInsurance.setPlan(submissionRequest.getPlan());
        travelInsurance.setPriceId(submissionRequest.getPriceId());
        travelInsurance.setStartDate(submissionRequest.getStartDate());
        travelInsurance.setEndDate(submissionRequest.getEndDate());
        travelInsurance.setTotalPrice(submissionRequest.getTotalPrice());

        return travelInsuranceService.submit(travelInsurance);
    }

    @PostMapping("plandetail/validate")
    @Operation(summary = "Validate plan details", description = "Validates the provided plan details")
    public PlanDetailValidationResponse planDetailValidation(@Valid @RequestBody PlanDetailRequest planDetailRequest) {
        return formService.planDetailValidation(planDetailRequest);
    }

    @PostMapping("customerdetail/validate")
    @Operation(summary = "Validate customer details", description = "Validates the provided customer details")
    public CustomerDetailValidationResponse customerDetailValidation(@Valid @RequestBody CustomerDetailRequest customerDetailRequest) {
        return formService.customerDetailValidation(customerDetailRequest);
    }
}
