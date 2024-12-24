package com.codegym.exam_final_md4.service;

import com.codegym.exam_final_md4.model.Deal;

import java.util.List;

public interface DealService {
    List<Deal> getAllDeals();
    Deal getDealById(Long id);
    Deal saveDeal(Deal deal);
    List<Deal> searchDeal(String customerName, Long serviceType);
    void deleteDeal(Long id);
}
