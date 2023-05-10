package ru.snowadv.javatw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.snowadv.javatw.entity.DriverDescription;
import ru.snowadv.javatw.entity.User;

public interface DriverDescriptionRepository extends JpaRepository<DriverDescription, Long> {

}
