package ru.cft.template.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.cft.template.Models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("update User u set u.firstName = :first, u.lastName = :last, u.email = :e where u.id = :userId")
    void setUserInfoById(@Param("first") String firstname,
                         @Param("last") String lastname,
                         @Param("e") String email,
                         @Param("userId") Long userId);
}
