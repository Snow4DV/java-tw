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

    public List<Car> allCars() {
        return carRepository.findAll();
    }


    public void addCar(Car car) {
        carRepository.save(car);
    }
}