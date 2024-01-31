package ru.cft.template.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.cft.template.Models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
