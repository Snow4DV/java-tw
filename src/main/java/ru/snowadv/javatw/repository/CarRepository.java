package ru.snowadv.javatw.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.snowadv.javatw.entity.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByOrderByManufacturerAsc();
}
