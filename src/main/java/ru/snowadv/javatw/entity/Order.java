package ru.snowadv.javatw.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "t_orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String destination;
    @ManyToOne
    @Nullable
    private DriverDescription assignedDriver;
    @ManyToMany
    private List<Offer> offers;
    private int price;
    private boolean done;
    private int leftRating = 0;
    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    private User user;

    public String getAssignedDriverUsername() {
        if(assignedDriver == null) {
            return "не назначен";
        }
        return assignedDriver.getUser().getUsername();
    }

    public Order(String address, int price, User user) {
        this.address = address;
        this.price = price;
        this.user = user;
    }
    public void addOffer(Offer offer) {
        offers.add(offer);
    }
}