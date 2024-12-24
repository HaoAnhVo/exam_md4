package com.codegym.exam_final_md4.service.impl;

import com.codegym.exam_final_md4.model.Customer;
import com.codegym.exam_final_md4.repository.ICustomerRepository;
import com.codegym.exam_final_md4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khách hàng với ID: " + id));
    }
}
