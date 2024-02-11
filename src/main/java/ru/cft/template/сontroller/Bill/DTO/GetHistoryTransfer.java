package ru.cft.template.сontroller.Bill.DTO;

import lombok.Data;
import ru.cft.template.model.Bill;

@Data
public class GetHistoryTransfer extends  GetHistory{
    private Long receiverNumber;
    public GetHistoryTransfer(Bill bill){
        super();
        this.setType(bill.getType());
        this.setReceiverNumber(bill.getUserTwo().getPhone());
    }
}
