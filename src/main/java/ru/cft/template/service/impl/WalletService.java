package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.cft.template.model.Bill;
import ru.cft.template.model.User;
import ru.cft.template.repository.BillRepository;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.IWallet;
import ru.cft.template.сontroller.Bill.DTO.GetBill;
import ru.cft.template.сontroller.Wallet.WalletTypes.*;

import java.nio.file.WatchKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.cft.template.сontroller.Wallet.WalletTypes.GetTransfer.*;

@Service
@AllArgsConstructor
public class WalletService implements IWallet {
    @Autowired
    private final WalletRepository walletRepo;
    private final BillRepository billRepo;
    private final UserRepository userRepo;

    @Override
    public GetWallet getBill(Long id){
        Bill bill = billRepo.findById(id).orElse(null);
        if (bill != null) return new GetWallet(bill);
        return null;
    }

    @Override
    @Transactional
    @Modifying
    public GetWallet updateBalance(Long userId, Long amount) throws Exception {
        walletRepo.hesoyam(userId, amount);
        User user = userRepo.findById(userId).orElse(null);
        if (user == null){
            System.out.println("Не существует пользователя с таким id");
            throw new Exception("Не существует пользователя с таким id");
        }
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setAmount(amount);

        billRepo.save(bill);
        return new GetWallet(bill);
//
//        return null;

    }
    @Transactional
    private void changeBalance(Wallet wallet, Long sum){
        wallet.setAmount(wallet.getAmount() + sum);
//        System.out.println(wallet.getAmount());
    }

    @Override
    @Transactional
    public GetTransfer transfer(@RequestBody PostTransfer data) throws Exception{
        Wallet walletSender = walletRepo.findByUserId(Long.parseLong(data.getUserId())).orElse(null);

        Wallet walletReceiver = walletRepo.findByPhone(data.getReceiverPhone()).orElse(null);

        if (walletSender == null || walletReceiver == null) {
            throw new Exception("Неправильный id отправителя или неправильный номер получателя");
        }
        if (walletSender.getAmount() - data.getAmount() < 0){
            throw new Exception("недостаточно средств");
        }

        if (data.getReceiverPhone() == null){
            Bill bill = new Bill();
            bill.setMaintenanceNumber(data.getMaintenanceNumber());
            bill.setUser(walletSender.getUser());
            bill.setType("inbound");
            bill.setAmount(data.getAmount());
            billRepo.save(bill);
        }
        changeBalance(walletSender, (-1) * data.getAmount());
        changeBalance(walletReceiver, data.getAmount());
        return new GetTransfer()
                .setUserId(data.getUserId())
                .setWallet(walletReceiver.getId().toString(), data.getAmount());
    }

    @Override
    public List<GetBill> getBillS(Long userId){
        List<Bill> bills = billRepo.getBills(userId);
        List<GetBill> getReq = new ArrayList<>();
        for (Bill bill : bills){
            getReq.add(new GetBill(bill));
        }
        return getReq;
    }

    @Override
    public GetMaintenance maintenancePost(PostMaintenance maintenance) throws Exception{
        User userSender = userRepo.findById(Long.parseLong(maintenance.getUserId()))
                .orElse(null);
        User userReceiver = userRepo.findByPhone(maintenance.getPhone())
                .orElse(null);

        if (userSender==null || userReceiver == null){
            throw new Exception("Нет такого получателя или отправителя");
        }

        Bill billSender = Bill.builder()
                .user(userSender)
                .amount(maintenance.getAmount())
                .type("outbound").build();
        Bill billReceiver = Bill.builder()
                .user(userReceiver)
                .amount(maintenance.getAmount())
                .type("inbound").build();

        billRepo.save(billSender);
        billRepo.save(billReceiver);

        GetMaintenance get = GetMaintenance.builder()
                .id(userReceiver.getId())
                .build();
        return get;
    }
}
