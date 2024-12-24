package com.codegym.exam_final_md4.service.impl;

import com.codegym.exam_final_md4.model.ServiceType;
import com.codegym.exam_final_md4.repository.IServiceTypeRepository;
import com.codegym.exam_final_md4.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {
    @Autowired
    private IServiceTypeRepository serviceTypeRepository;

    @Override
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }
}
