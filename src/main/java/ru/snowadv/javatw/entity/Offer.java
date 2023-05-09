package ru.snowadv.javatw.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "t_offers")
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    Order boundOrder;
    @ManyToOne
    DriverDescription driver;
    Integer price;

    public Offer(Order boundOrder, DriverDescription driver, Integer price) {
        this.boundOrder = boundOrder;
        this.driver = driver;
        this.price = price;
    }


}