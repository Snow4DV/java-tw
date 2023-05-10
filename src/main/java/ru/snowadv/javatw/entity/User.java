package ru.snowadv.javatw.entity;

import lombok.Getter;
import lombok.Setter;

import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_user")
@Getter
@Setter
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;

    public String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @Nullable
    @JoinColumn(name = "driver_description_id", referencedColumnName = "id")
    private DriverDescription driverDescription;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders;

    public User() {
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public boolean isDriver() {
        return roles.stream().anyMatch(role -> role.getName().equals("ROLE_DRIVER"));
    }
    public String getRoleString() {
        if(roles.stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))) {
            return "Администратор";
        } else if(roles.stream().anyMatch(role -> role.getName().equals("ROLE_DRIVER"))) {
            return "Водитель";
        } else if(roles.stream().anyMatch(role -> role.getName().equals("ROLE_USER"))) {
            return "Пассажир";
        } else {
            return "Неизвестный";
        }
    }
    public void  addOrder(Order order) {
        orders.add(order);
    }

}
