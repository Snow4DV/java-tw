package ru.snowadv.javatw.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "t_driver")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DriverDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double rating = 0.0;
    private Integer reviewsCount = 0;
    private String drivinglicenseNumber;
    private String carCertificateOfOwnership;
    @ManyToOne
    private Car car;
    private Integer serviceRulesViolations = 0;
    @OneToOne(mappedBy = "driverDescription")
    private User user;


    public void addRating(int rating) {
        if(++reviewsCount == 1) { // if it is the first review
            this.rating = (double) rating;
        } else {
            this.rating = (this.rating + rating) / reviewsCount;
        }
    }

    public String getStringRating() {
        return String.format("%.2f", getRating());
    }

    public User getUser() {
        return user;
    }
}