package com.codegym.exam_final_md4.service;

import com.codegym.exam_final_md4.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
}
