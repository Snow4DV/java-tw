package ru.snowadv.javatw.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.snowadv.javatw.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(String name);
}
