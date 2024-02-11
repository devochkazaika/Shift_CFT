package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
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
        bill.setType("update");

        billRepo.save(bill);
        return new GetWallet(bill);
//
//        return null;

    }
    @Transactional
    private void changeBalance(Wallet wallet, Long sum){
        wallet.setAmount(wallet.getAmount() + sum);
    }

    @Override
    @Transactional
    @Modifying
    public GetTransfer transfer(@RequestBody PostTransfer data) throws Exception{
        if (data.getReceiverPhone() == null){
            return transferToBill(data);
        }
        else {
            return transferToPhone(data);
        }

    }

    private GetTransfer transferToBill(PostTransfer data){
        Wallet walletSender = walletRepo.findByUserId(Long.parseLong(data.getUserId())).orElse(null);
        if (walletSender == null) {
            throw new NullPointerException("Неправильный id отправителя");
        }

        Bill bill = billRepo.findById(data.getMaintenanceNumber()).orElse(null);

        if (bill != null){
            billRepo.changeAmountRemains(bill.getId(), data.getAmount());
            if (bill.getAmountRemains() <= 0){
                billRepo.changeStatus(data.getMaintenanceNumber());
            }
            System.out.println(bill.getAmountRemains());
        }
        else throw new NullPointerException("неправильный номер счёта");

        return new GetTransfer()
                .setId(bill.getId().toString())
                .setUserId(data.getUserId())
                .setWallet(walletSender.getId().toString(), data.getAmount());
    }
    private GetTransfer transferToPhone(PostTransfer data) throws Exception{
        Wallet walletReceiver = walletRepo.findByPhone(data.getReceiverPhone()).orElse(null);
        Wallet walletSender = walletRepo.findById(Long.parseLong(data.getUserId())).orElse(null);

        Bill bill = new Bill();
        bill.setUser(walletSender.getUser());
        bill.setType("transfer");
        bill.setAmount(data.getAmount());
        billRepo.save(bill);

        if (walletSender.getAmount() - data.getAmount() < 0){
            throw new Exception("недостаточно средств");
        }
        if ( walletReceiver == null) {
            throw new NullPointerException("Неправильный id получателя");
        }

        billRepo.changeAmountRemains(bill.getId(), bill.getAmount()-data.getAmount());
        if (bill.getAmountRemains() <= 0){
            billRepo.changeStatus(data.getMaintenanceNumber());
        }
        changeBalance(walletReceiver, data.getAmount());
        return new GetTransfer()
                .setId(bill.getId().toString())
                .setUserId(data.getUserId())
                .setWallet(walletSender.getId().toString(), data.getAmount());

    }


}
