package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.template.model.Bill;
import ru.cft.template.model.User;
import ru.cft.template.repository.BillRepository;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.сontroller.Bill.DTO.GetHistory;
import ru.cft.template.сontroller.Bill.DTO.GetHistoryPayment;
import ru.cft.template.сontroller.Bill.DTO.GetHistoryTransfer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HistoryService {
    @Autowired
    private final BillRepository historyRepo;
    @Autowired
    private final UserRepository userRepo;
    @Autowired
    private final BillRepository billRepo;
    public List<GetHistory> getHistory(Long id) throws Exception{
        User user = userRepo.findById(id).orElse(null);
        if (user == null) throw new NullPointerException("Пользователя с таким id не существует");

        List<GetHistory> history = new ArrayList<>();
        for (Bill bill : billRepo.getBills(id)){
            if (bill.getType().equals("transfer")){

                history.add(new GetHistoryTransfer(bill));
            }
            else if (bill.getType().equals("payment")){
                history.add(new GetHistoryPayment(bill));
            }
//            history.add(bill);
        }
        return history;
    }
}
