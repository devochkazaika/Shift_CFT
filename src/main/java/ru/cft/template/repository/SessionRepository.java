package ru.cft.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cft.template.model.Session;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("select s from Session s where s.userId = :id")
    List<Session> getUserSession(@Param("id") Long id);

//    @Modifying
//    @Query("INSERT INTO User (userId, expirationTime, active) Values(:userId, :expirationTime, :active)")
//    void insert(@Param("userId") Long userId,
//                              @Param("expirationTime") Date expirationTime,
//                              @Param("active") Integer active);
}
