package ru.cft.template.сontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.template.service.impl.HistoryService;
import ru.cft.template.service.impl.UserService;
import ru.cft.template.сontroller.Bill.DTO.GetHistory;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/history")
public class History {
    @Autowired
    private final HistoryService historyService;
    @GetMapping("/{id}")
    public List<GetHistory> getHistories(@PathVariable Long id) throws Exception{
        return historyService.getHistory(id);
    }
}
