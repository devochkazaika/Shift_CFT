package ru.cft.template.сontroller.Bill.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public abstract class GetHistory {
    private Long id;
    private Long userId;
    private String transactionDate;
    private String type;
    private String status;
}
