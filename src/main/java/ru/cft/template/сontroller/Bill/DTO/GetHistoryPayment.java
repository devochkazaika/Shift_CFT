package ru.cft.template.сontroller.Bill.DTO;

import lombok.Data;
import ru.cft.template.model.Bill;

@Data
public class GetHistoryPayment extends GetHistory{
    private Long maintenanceNumber;
    public GetHistoryPayment(Bill bill){
        super();
        this.setType(bill.getType());
        this.setMaintenanceNumber(bill.getMaintenanceNumber());
    }
}
