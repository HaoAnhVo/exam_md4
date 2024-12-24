package com.codegym.exam_final_md4.repository;

import com.codegym.exam_final_md4.model.ServiceType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServiceTypeRepository {
    List<ServiceType> findAll();
}
