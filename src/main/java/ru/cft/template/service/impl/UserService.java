package ru.cft.template.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.dao.UserRepository;
import ru.cft.template.dao.WalletRepository;
import ru.cft.template.model.User;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.impl.IUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUser {
    private final UserRepository userRepo;
    private final WalletRepository walletRepo;
    @Override
    public Optional<User> findById(Long id) {

        return userRepo.findById(id);
    }
    @Override
    public List<User> findAll() {

        return userRepo.findAll();
    }
    @Override
    public void save(User user) {
        Wallet wallet = new Wallet();
        user.setWallet(wallet);
        wallet.setAmount(0L);
        wallet.setId(user.getId());
        wallet.setUser(user);
        wallet.setLastUpdate(LocalDate.now());

        userRepo.save(user);
        walletRepo.save(wallet);
    }
    @Override
    @Transactional
    public void update(Long id, User user) {
        userRepo.setUserInfoById(user.getFirstName(), user.getLastName(), user.getEmail(),
                id);
    }
    @Override
    public User findByPhone(Integer phone){
        return userRepo.findByPhone(phone).get(0);
    }
}
