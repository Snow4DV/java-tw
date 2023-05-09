package ru.snowadv.javatw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.snowadv.javatw.entity.DriverDescription;
import ru.snowadv.javatw.entity.Offer;
import ru.snowadv.javatw.entity.Order;
import ru.snowadv.javatw.entity.User;
import ru.snowadv.javatw.repository.OfferRepository;
import ru.snowadv.javatw.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;


    public List<Offer> getOffersByDriver(DriverDescription driver) {
        return offerRepository.getOfferByDriver(driver);
    }

    public Offer getOfferById(Long id) {
        return offerRepository.findById(id).orElse(null);
    }
    public void save(Offer offer) {
        offerRepository.save(offer);
    }
    public List<Offer> getOffersByDriverId(Long id) {
        return offerRepository.getOfferByDriver_Id(id);
    }
    public Offer getFirstOfferByDriver(DriverDescription driver) {
        return offerRepository.findFirstByDriver(driver);
    }

    public Offer getFirstOfferByDriverId(Long driverId) {
        return offerRepository.findFirstByDriver_Id(driverId);
    }
}