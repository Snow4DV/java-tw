package ru.snowadv.javatw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.snowadv.javatw.entity.DriverDescription;
import ru.snowadv.javatw.entity.Order;
import ru.snowadv.javatw.entity.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByDoneFalseAndUser_Id(Long id);
    List<Order> findByDoneFalseAndUser(User user);
    List<Order> findAllByAssignedDriver_Id(Long id);
    List<Order> findAllByAssignedDriver(DriverDescription driver);
    List<Order> findAllByDoneFalseAndAssignedDriverNull();
    Order findFirstByAssignedDriverAndDoneIsFalse(DriverDescription driver);
    Order findFirstByUserAndDoneIsFalse(User user);


    List<Order> findAllByDoneTrueAndAssignedDriver(DriverDescription driver);
    List<Order> findAllByDoneTrueAndUser(User user);
}
