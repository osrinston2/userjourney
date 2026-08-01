package com.project.userjourney.service;

import com.project.userjourney.dto.SubmissionRequest;
import com.project.userjourney.model.Customer;
import com.project.userjourney.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(SubmissionRequest submissionRequest) {
        Customer customer = new Customer();
        SubmissionRequest.CustomerDetail customerDetail = submissionRequest.getCustomer();

        customer.setFullName(customerDetail.getFullName());
        customer.setEmail(customerDetail.getEmail());
        customer.setMobileNo(customerDetail.getMobileNo());
        customer.setNric(customerDetail.getNric());
        customer.setDateOfBirth(customerDetail.getDateOfBirth());
        customer.setGender(customerDetail.getGender());
        customer.setAddressLine1(customerDetail.getAddressLine1());
        customer.setAddressLine2(customerDetail.getAddressLine2());
        customer.setPostCode(customerDetail.getPostCode());

        return customerRepository.save(customer);
    }
}
