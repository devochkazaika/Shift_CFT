package ru.cft.template.сontroller.Bill.DTO;


import lombok.Data;
import ru.cft.template.model.Bill;

import java.time.LocalDate;

@Data
public class GetBill {
    private Long id;
    private String type;
    private Long amount;
    private Long maintenanceNumber;
    private Boolean status;
    private LocalDate transactionDate;

    public GetBill(Bill bill){
        id = bill.getId();
        type = bill.getType();
        amount = bill.getAmount();
        maintenanceNumber = bill.getMaintenanceNumber();
        transactionDate = bill.getTransactionDate();
        status = bill.getStatus();
    }
}
