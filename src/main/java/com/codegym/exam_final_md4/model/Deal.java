package com.codegym.exam_final_md4.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "deal")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Transient
    private String formattedPrice;

    @Column(name = "acreage")
    @DecimalMin(value = "20", message = "Diện tích phải lớn hơn 20 m2.")
    @NotNull(message = "Diện tích không được để trống.")
    private BigDecimal acreage;

    @Column(name = "deal_code")
    @Pattern(regexp = "MGD-\\d{4}", message = "Mã giao dịch phải theo định dạng MGD-XXXX.")
    @NotBlank(message = "Mã giao dịch không được để trống.")
    private String dealCode;
}
