package ru.snowadv.javatw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.snowadv.javatw.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
