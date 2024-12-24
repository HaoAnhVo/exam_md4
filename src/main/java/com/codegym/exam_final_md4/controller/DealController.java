package com.codegym.exam_final_md4.controller;

import com.codegym.exam_final_md4.formatter.DateFormatterUtil;
import com.codegym.exam_final_md4.model.Customer;
import com.codegym.exam_final_md4.model.Deal;
import com.codegym.exam_final_md4.model.ServiceType;
import com.codegym.exam_final_md4.service.impl.CustomerServiceImpl;
import com.codegym.exam_final_md4.service.impl.DealServiceImpl;
import com.codegym.exam_final_md4.service.impl.ServiceTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/deal")
public class DealController {

    @Autowired
    private DealServiceImpl dealService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private ServiceTypeServiceImpl serviceTypeService;

    @ModelAttribute("customers")
    public Iterable<Customer> listCustomers() {
        return customerService.getAllCustomers();
    }

    @ModelAttribute("serviceTypes")
    public Iterable<ServiceType> listServiceTypes() {
        return serviceTypeService.getAllServiceTypes();
    }

    @GetMapping
    public String dealList(Model model) {
        List<Deal> deals = dealService.getAllDeals();

        for (Deal deal : deals) {
            String formattedDate = DateFormatterUtil.formatLocalDate(deal.getDateOfDeal());
            deal.setFormattedDate(formattedDate);
        }
        model.addAttribute("deals", deals);
        return "/deal/list";
    }

    @GetMapping("/{id}")
    public String dealDetail(@PathVariable Long id, Model model) {
        Deal deal = dealService.getDealById(id);

        String formattedDate = DateFormatterUtil.formatLocalDate(deal.getDateOfDeal());
        model.addAttribute("formattedDate", formattedDate);
        model.addAttribute("deal", deal);
        model.addAttribute("customer", deal.getCustomer());
        return "/deal/detail";
    }

    @GetMapping("/new")
    public String newDeal(Model model) {
        model.addAttribute("deal", new Deal());
        model.addAttribute("serviceTypes", serviceTypeService.getAllServiceTypes());
        return "/deal/new";
    }

    @PostMapping("/new")
    public String saveDeal(
            @Valid @ModelAttribute("deal") Deal deal,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "/deal/new";
        }

        dealService.saveDeal(deal);
        redirectAttributes.addFlashAttribute("message", "Thêm mới giao dịch thành công!");
        return "redirect:/deal";
    }

    @GetMapping("/delete/{id}")
    public String deleteDeal(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            dealService.deleteDeal(id);
            redirectAttributes.addFlashAttribute("message", "Giao dịch đã được xóa!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa giao dịch!");
        }
        return "redirect:/deal";
    }

    @PostMapping("/search")
    public String searchDeal(@RequestParam(value = "customerName", required = false) String customerName,
                             @RequestParam(value = "serviceType", required = false) Long serviceTypeId,
                             Model model) {
        if (customerName == null || customerName.isEmpty()) {
            customerName = null;
        }

        if (serviceTypeId == null || serviceTypeId == 0) {
            serviceTypeId = null;
        }

        List<Deal> deals = dealService.searchDeal(customerName, serviceTypeId);
        List<ServiceType> serviceTypes = serviceTypeService.getAllServiceTypes();
        model.addAttribute("deals", deals);
        model.addAttribute("serviceTypes", serviceTypes);
        model.addAttribute("customerName", customerName);
        model.addAttribute("serviceTypeId", serviceTypeId);
        return "/deal/list";
    }


}
