package ru.cft.template.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.model.User;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.IUser;
import ru.cft.template.сontroller.User.UserTypes.GetUsers;
import ru.cft.template.сontroller.User.UserTypes.UserPatch;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUser {
    private final UserRepository userRepo;
    private final WalletRepository walletRepo;
    @Override
    public GetUsers getUser(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) return new GetUsers(user);
        return null;
    }
    @Override
    public List<GetUsers> getUsers() {
        List<User> users = userRepo.findAll();
        List<GetUsers> userRequest = new ArrayList<GetUsers>();
        for (User user : users){
            userRequest.add(new GetUsers(user));
        }
        return userRequest;
//        return userRepo.findAll();
    }
    @Override
    public void save(User user) throws Exception {
        if (userRepo.findByPhone(user.getPhone()).orElse(null) == null) {
            Wallet wallet = new Wallet();
            user.setWallet(wallet);
            wallet.setAmount(0L);
            wallet.setId(user.getId());
            wallet.setUser(user);
            wallet.setLastUpdate(LocalDate.now());

            userRepo.save(user);
            walletRepo.save(wallet);
        }
        else throw new Exception("Данный номер существует");
    }
    @Override
    @Transactional
    public GetUsers update(Long id, UserPatch userPatch) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setFirstName(userPatch.getFirstName());
            user.setLastName(userPatch.getLastName());
            user.setFullName(userPatch.getFirstName() + " " + userPatch.getLastName());
            user.setEmail(userPatch.getEmail());
            user.setLastUpdateDate(LocalDate.now());
            userRepo.setUserInfoById(user.getFirstName(), user.getLastName(), user.getEmail(), id);
            return new GetUsers(user);
        }
        return null;
//        userRepo.setUserInfoById(user.getFirstName(), user.getLastName(), user.getEmail(),
//                id);
    }
    @Override
    public User findByPhone(Long phone){
        return userRepo.findByPhone(phone).orElse(null);
    }
}
