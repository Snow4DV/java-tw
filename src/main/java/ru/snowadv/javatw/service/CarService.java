package ru.snowadv.javatw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.snowadv.javatw.entity.Car;
import ru.snowadv.javatw.repository.CarRepository;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;


    public List<Car> allCarsSorted() {
        return carRepository.findAllByOrderByManufacturerAsc();
    }

    public Car getCarById(Long id) {
        return carRepository.getReferenceById(id);
    }


}