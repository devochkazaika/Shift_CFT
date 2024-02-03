package ru.cft.template.service;

import org.springframework.data.repository.query.Param;
import ru.cft.template.model.Session;
import ru.cft.template.model.User;
import ru.cft.template.сontroller.Session.Autorisation.Autorisation;
import ru.cft.template.сontroller.Session.Autorisation.PhoneAndPassword;
import ru.cft.template.сontroller.Session.SessionTypes.SessionDTO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ISession {
    Optional<Session> findById(Long id);
    List<SessionDTO> getSessions();
    void save(Session session);

    Autorisation save(PhoneAndPassword token);
    List<Session> getUserSession(@Param("id") Long id);

    void delete(Long id);

//    void insert(Long userId, Date expirationTime, Integer active);
}
