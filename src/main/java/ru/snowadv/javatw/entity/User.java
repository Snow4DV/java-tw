package ru.snowadv.javatw.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
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
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @Nullable
    @JoinColumn(name = "driver_description_id", referencedColumnName = "id")
    private DriverDescription driverDescription;

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

    public String getRoleString() {
        Role role = roles.stream().findFirst().orElse(null);

        if(role == null) {
            return "Не установлена";
        }

        switch(role.getName()) {
            case "ROLE_USER":
                return "Пассажир";
            case "ROLE_DRIVER":
                return "Водитель";
            default:
                return "Другая роль";
        }
    }

}
