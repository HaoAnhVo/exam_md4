package com.codegym.exam_final_md4.repository;

import com.codegym.exam_final_md4.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IServiceTypeRepository extends JpaRepository<ServiceType, Long> {
}
