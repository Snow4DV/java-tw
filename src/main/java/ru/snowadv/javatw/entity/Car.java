package ru.snowadv.javatw.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "t_cars")
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    private Long id;
    private String manufacturer;
    private String model;
    public Integer yearStartOfManufacturing;
    public Integer yearEndOfManufacturing;
}