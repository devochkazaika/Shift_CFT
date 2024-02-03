package ru.cft.template.service;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.cft.template.dao.SessionRepository;
import ru.cft.template.dao.UserRepository;
import ru.cft.template.model.Session;
import ru.cft.template.service.impl.ISession;

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
    public List<Session> findAll() {
        return sessionRepo.findAll();
    }

    @Override
    public void save(Session session) {
        sessionRepo.save(session);
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
