package ru.snowadv.javatw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.snowadv.javatw.entity.DriverDescription;
import ru.snowadv.javatw.entity.Offer;
import ru.snowadv.javatw.entity.Order;
import ru.snowadv.javatw.entity.User;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> getOfferByDriver(DriverDescription driver);
    Offer findFirstByDriver(DriverDescription driver);
    List<Offer> getOfferByDriver_Id(Long id);
    Offer findFirstByDriver_Id(Long id);
}
