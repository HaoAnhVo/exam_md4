package com.codegym.exam_final_md4.controller;

import com.codegym.exam_final_md4.model.Customer;
import com.codegym.exam_final_md4.model.Deal;
import com.codegym.exam_final_md4.model.ServiceType;
import com.codegym.exam_final_md4.service.ServiceTypeService;
import com.codegym.exam_final_md4.service.impl.CustomerServiceImpl;
import com.codegym.exam_final_md4.service.impl.DealServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/deal")
public class DealController {

    @Autowired
    private DealServiceImpl dealService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @ModelAttribute("customers")
    public Iterable<Customer> listCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping
    public String dealList(Model model) {
        model.addAttribute("deals", dealService.getAllDeals());
        return "/deal/list";
    }

    @GetMapping("/{id}")
    public String dealDetail(@PathVariable Long id, Model model) {
        Deal deal = dealService.getDealById(id);
        model.addAttribute("deal", deal);
        model.addAttribute("customer", deal.getCustomer());
        return "/deal/detail";
    }

    @GetMapping("/new")
    public String newDeal(Model model) {
        model.addAttribute("deal", new Deal());
        model.addAttribute("serviceTypes", serviceTypeService.findAll());
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
        List<Deal> deals = dealService.searchDeal(customerName, serviceTypeId);
        List<ServiceType> serviceTypes = serviceTypeService.findAll();
        model.addAttribute("deals", deals);
        model.addAttribute("serviceTypes", serviceTypes);
        model.addAttribute("customerName", customerName);
        model.addAttribute("serviceTypeId", serviceTypeId);
        return "/deal/list";
    }


}
