package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.cft.template.model.User;
import ru.cft.template.repository.SessionRepository;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.model.Session;
import ru.cft.template.service.ISession;
import ru.cft.template.сontroller.Session.Autorisation.Autorisation;
import ru.cft.template.сontroller.Session.Autorisation.PhoneAndPassword;
import ru.cft.template.сontroller.Session.SessionTypes.SessionDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionService implements ISession {
    private final SessionRepository sessionRepo;
    private final UserRepository userRepo;
    @Override
    public Optional<Session> findById(Long id) {
        return sessionRepo.findById(id);
    }
    @Override
    public List<SessionDTO> getSessions() {
        List<SessionDTO> sessionRequest = new ArrayList<SessionDTO>();
        for (Session session : sessionRepo.findAll()){
            sessionRequest.add(new SessionDTO(session));
        }
        return sessionRequest;
    }

    @Override
    public void save(Session session) {
        sessionRepo.save(session);
    }
    @Override
    public Autorisation save(PhoneAndPassword token) {
        Autorisation autorisation = new Autorisation();
        User u = userRepo.findByPhone(token.getPhone()).orElse(null);
        if (u != null && (u.getPassword() == null || u.getPassword().equals(token.getPassword()))){
            Session k = new Session();
            k.setExpirationTime(LocalDate.now().plusDays(3));
            k.setActive(1);
            k.setUserId(u.getId());
            sessionRepo.save(k);
            autorisation.setId(k.getId());
            autorisation.setExpirationTime(k.getExpirationTime());
            autorisation.setToken("ASAS");
            autorisation.setUserId(k.getUserId());
            return autorisation;
        }
        return null;
    }
    @Override
    public List<Session> getUserSession(@Param("id") Long id){
        return sessionRepo.getUserSession(id);
    }

//    @Override
//    public void insert(Long userId, Date expirationTime, Integer active){
//        sessionRepo.insert(userId, expirationTime, active);
//    }
    @Override
    public void delete(Long id){
        sessionRepo.deleteById(id);
    }
}
