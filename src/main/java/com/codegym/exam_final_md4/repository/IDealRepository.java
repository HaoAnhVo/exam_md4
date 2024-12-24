package com.codegym.exam_final_md4.repository;

import com.codegym.exam_final_md4.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findByCustomerFullnameContainingAndServiceTypeId(String customerName, Long serviceTypeId);
    List<Deal> findByCustomerFullnameContaining(String customerName);
    List<Deal> findByServiceTypeId(Long serviceTypeId);
    List<Deal> findAll();
}
