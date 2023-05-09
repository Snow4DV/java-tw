package ru.snowadv.javatw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.snowadv.javatw.entity.Car;
import ru.snowadv.javatw.entity.DriverDescription;
import ru.snowadv.javatw.entity.Order;
import ru.snowadv.javatw.entity.User;
import ru.snowadv.javatw.repository.CarRepository;
import ru.snowadv.javatw.repository.OrderRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> getActiveOrdersByDriverId(Long id) {
        return orderRepository.findAllByAssignedDriver_Id(id);
    }

    public List<Order> getActiveOrdersByUserId(Long id) {
        return orderRepository.findByDoneFalseAndUser_Id(id);
    }

    public List<Order> getActiveOrdersByDriver(DriverDescription driver) {
        return orderRepository.findAllByAssignedDriver(driver);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getActiveOrdersByUser(User user) {
        return orderRepository.findByDoneFalseAndUser(user);
    }
    public List<Order> getUnassignedOrders() {
        return orderRepository.findAllByDoneFalseAndAssignedDriverNull();
    }

    public Order getAssignedUnfinishedOrderByDriver(DriverDescription driver) {
        return orderRepository.findFirstByAssignedDriverAndDoneIsFalse(driver);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public Order getFirstUnfinishedOrderByUser(User user) {
        return orderRepository.findFirstByUserAndDoneIsFalse(user);
    }

    public List<Order> getAllDoneByDriver(DriverDescription driver) {
        return orderRepository.findAllByDoneTrueAndAssignedDriver(driver);
    }
    public List<Order> getAllDoneByUser(User user) {
        return orderRepository.findAllByDoneTrueAndUser(user);
    }

}