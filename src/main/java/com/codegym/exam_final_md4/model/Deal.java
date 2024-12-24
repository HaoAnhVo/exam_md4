package com.codegym.exam_final_md4.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "deal")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "Tên khách hàng không được để trống.")
    private Customer customer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_deal", nullable = false)
    @Future(message = "Ngày giao dịch phải lớn hơn ngày hiện tại.")
    @NotNull(message = "Ngày giao dịch không được để trống.")
    private LocalDate dateOfDeal;

    @Transient
    private String formattedDate;

    @ManyToOne
    @JoinColumn(name = "service_type_id", nullable = false)
    @NotNull(message = "Loại dịch vụ không được để trống.")
    private ServiceType  serviceType;

    @Column(name = "price")
    @DecimalMin(value = "500000", message = "Đơn giá phải lớn hơn 500.000 VND.")
    @NotNull(message = "Đơn giá không được để trống.")
    private BigDecimal price;

    @Column(name = "acreage")
    @DecimalMin(value = "20", message = "Diện tích phải lớn hơn 20 m2.")
    @NotNull(message = "Diện tích không được để trống.")
    private BigDecimal acreage;

    @Column(name = "deal_code")
    @Pattern(regexp = "MGD-\\d{4}", message = "Mã giao dịch phải theo định dạng MGD-XXXX.")
    @NotBlank(message = "Mã giao dịch không được để trống.")
    private String dealCode;

    public Deal() {
    }

    public Deal(Long id, Customer customer, LocalDate dateOfDeal, String formattedDate, ServiceType serviceType, BigDecimal price, BigDecimal acreage, String dealCode) {
        this.id = id;
        this.customer = customer;
        this.dateOfDeal = dateOfDeal;
        this.formattedDate = formattedDate;
        this.serviceType = serviceType;
        this.price = price;
        this.acreage = acreage;
        this.dealCode = dealCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Tên khách hàng không được để trống.") Customer getCustomer() {
        return customer;
    }

    public void setCustomer(@NotNull(message = "Tên khách hàng không được để trống.") Customer customer) {
        this.customer = customer;
    }

    public @Future(message = "Ngày giao dịch phải lớn hơn ngày hiện tại.") @NotNull(message = "Ngày giao dịch không được để trống.") LocalDate getDateOfDeal() {
        return dateOfDeal;
    }

    public void setDateOfDeal(@Future(message = "Ngày giao dịch phải lớn hơn ngày hiện tại.") @NotNull(message = "Ngày giao dịch không được để trống.") LocalDate dateOfDeal) {
        this.dateOfDeal = dateOfDeal;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public @NotNull(message = "Loại dịch vụ không được để trống.") ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(@NotNull(message = "Loại dịch vụ không được để trống.") ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public @DecimalMin(value = "500000", message = "Đơn giá phải lớn hơn 500.000 VND.") @NotNull(message = "Đơn giá không được để trống.") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@DecimalMin(value = "500000", message = "Đơn giá phải lớn hơn 500.000 VND.") @NotNull(message = "Đơn giá không được để trống.") BigDecimal price) {
        this.price = price;
    }

    public @DecimalMin(value = "20", message = "Diện tích phải lớn hơn 20 m2.") @NotNull(message = "Diện tích không được để trống.") BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(@DecimalMin(value = "20", message = "Diện tích phải lớn hơn 20 m2.") @NotNull(message = "Diện tích không được để trống.") BigDecimal acreage) {
        this.acreage = acreage;
    }

    public @Pattern(regexp = "MGD-\\d{4}", message = "Mã giao dịch phải theo định dạng MGD-XXXX.") @NotBlank(message = "Mã giao dịch không được để trống.") String getDealCode() {
        return dealCode;
    }

    public void setDealCode(@Pattern(regexp = "MGD-\\d{4}", message = "Mã giao dịch phải theo định dạng MGD-XXXX.") @NotBlank(message = "Mã giao dịch không được để trống.") String dealCode) {
        this.dealCode = dealCode;
    }
}
