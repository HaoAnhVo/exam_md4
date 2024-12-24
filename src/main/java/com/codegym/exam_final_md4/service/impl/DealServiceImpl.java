package com.codegym.exam_final_md4.service.impl;

import com.codegym.exam_final_md4.model.Deal;
import com.codegym.exam_final_md4.repository.IDealRepository;
import com.codegym.exam_final_md4.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    @Autowired
    private IDealRepository dealRepository;

    @Override
    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal getDealById(Long id) {
        return dealRepository.findById(id).orElse(null);
    }

    @Override
    public Deal saveDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    public void deleteDeal(Long id) {
        Deal deal = dealRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Giao dịch không tồn tại"));
        dealRepository.delete(deal);
    }

    @Override
    public List<Deal> searchDeal(String customerName, Long serviceTypeId) {
        if (customerName != null && serviceTypeId != null) {
            return dealRepository.findByCustomerFullnameContainingAndServiceTypeId(customerName, serviceTypeId);
        } else if (customerName != null) {
            return dealRepository.findByCustomerFullnameContaining(customerName);
        } else if (serviceTypeId != null) {
            return dealRepository.findByServiceTypeId(serviceTypeId);
        } else {
            return dealRepository.findAll();
        }
    }
}
