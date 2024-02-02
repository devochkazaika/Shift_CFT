package ru.cft.template.service.impl;

import org.springframework.data.repository.query.Param;
import ru.cft.template.model.Session;
import ru.cft.template.model.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ISession {
    Optional<Session> findById(Long id);
    List<Session> findAll();
    void save(Session session);
    List<Session> getUserSession(@Param("id") Long id);

    void delete(Long id);

//    void insert(Long userId, Date expirationTime, Integer active);
}
