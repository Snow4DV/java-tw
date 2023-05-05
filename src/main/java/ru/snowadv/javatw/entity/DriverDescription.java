package ru.snowadv.javatw.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "t_driver")
@NoArgsConstructor
@AllArgsConstructor
public class DriverDescription {

    @Id
    private Long id;
    private Double rating;
    @ManyToOne
    private Car car;
    private Integer serviceRulesViolations;
    @OneToOne(mappedBy = "t_driver")
    private User user;



}