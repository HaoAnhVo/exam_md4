package com.codegym.exam_final_md4.service.impl;

import com.codegym.exam_final_md4.formatter.CurrencyFormatterUtil;
import com.codegym.exam_final_md4.formatter.DateFormatterUtil;
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
        List<Deal> deals = dealRepository.findAll();
        return formatDeals(deals);
    }

    @Override
    public Deal getDealById(Long id) {
        Deal deal = dealRepository.findById(id).orElse(null);
        if (deal != null) {
            formatDeal(deal);
        }
        return deal;
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
        List<Deal> deals;
        if (customerName != null && serviceTypeId != null) {
            deals = dealRepository.findByCustomerFullnameContainingAndServiceTypeId(customerName, serviceTypeId);
        } else if (customerName != null) {
            deals = dealRepository.findByCustomerFullnameContaining(customerName);
        } else if (serviceTypeId != null) {
            deals = dealRepository.findByServiceTypeId(serviceTypeId);
        } else {
            deals = dealRepository.findAll();
        }
        return formatDeals(deals);
    }

    private List<Deal> formatDeals(List<Deal> deals) {
        for (Deal deal : deals) {
            formatDeal(deal);
        }
        return deals;
    }

    private void formatDeal(Deal deal) {
        String formattedDate = DateFormatterUtil.formatLocalDate(deal.getDateOfDeal());
        deal.setFormattedDate(formattedDate);
        String formattedPrice = CurrencyFormatterUtil.formatToVND(deal.getPrice());
        deal.setFormattedPrice(formattedPrice);
    }
}
