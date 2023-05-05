package ru.snowadv.javatw.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.snowadv.javatw.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
