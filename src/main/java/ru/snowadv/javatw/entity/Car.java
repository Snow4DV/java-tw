package ru.snowadv.javatw.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "t_cars")
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
    private String model;
    public Integer yearStartOfManufacturing;
    public Integer yearEndOfManufacturing;

    public String toString() {
        return String.format("%s %s (%s - %s)", getManufacturer(), getModel(),
                getYearStartOfManufacturing() == null ? "неизв." : getYearStartOfManufacturing(),
                getYearEndOfManufacturing() == null ? "сегодня" : getYearEndOfManufacturing());
    }
}