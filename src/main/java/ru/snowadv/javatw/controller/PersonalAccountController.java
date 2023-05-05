package ru.snowadv.javatw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.snowadv.javatw.entity.DriverDescription;
import ru.snowadv.javatw.entity.Role;
import ru.snowadv.javatw.entity.User;
import ru.snowadv.javatw.service.CarService;
import ru.snowadv.javatw.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class PersonalAccountController {


    private final UserService userService;

    private final CarService carService;

    @GetMapping
    public String showPersonalAccountPage(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "account/personal-account";
    }

    @GetMapping("/delete")
    public String deleteAccount() {
        userService.deleteUser(userService.getCurrentUser().getId());
        return "redirect:/";
    }


    @GetMapping("/become-driver")
    public String getBecomeDriverPage() {
        return "account/become-driver";
    }

    @GetMapping("/add-driver-info")
    public String getAddDriverDescriptionPage(Model model) {
        User currentUser = userService.getCurrentUser();
        Set<Role> roles = currentUser.getRoles();
        if(roles.stream().noneMatch(role -> role.getName().equals("DRIVER"))) {
            model.addAttribute("justBecameDriver", true);
            roles.add(userService.getRoleByName("ROLE_DRIVER"));
        } else {
            model.addAttribute("justBecameDriver", false);
        }
        if(currentUser.getDriverDescription() == null) {
            currentUser.setDriverDescription(new DriverDescription());
        }
        model.addAttribute("driverDescription", currentUser.getDriverDescription());
        model.addAttribute("cars", carService.allCars());
        return "account/add-driver-info";
    }
}