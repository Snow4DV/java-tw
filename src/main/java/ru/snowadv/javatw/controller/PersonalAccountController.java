package ru.snowadv.javatw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.snowadv.javatw.entity.Car;
import ru.snowadv.javatw.entity.DriverDescription;
import ru.snowadv.javatw.entity.Role;
import ru.snowadv.javatw.entity.User;
import ru.snowadv.javatw.service.CarService;
import ru.snowadv.javatw.service.UserService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        return "redirect:/logout";
    }


    @GetMapping("/become-driver")
    public String getBecomeDriverPage() {
        return "account/become-driver";
    }


    @PostMapping("/add-driver-info")
    public String saveDriverInfo(DriverDescription driverDescription, @RequestParam("carId") String carId) {
        driverDescription.setCar(carService.getCarById(Long.valueOf(carId)));
        userService.getCurrentUser().setDriverDescription(driverDescription);
        userService.save(userService.getCurrentUser());
        return "redirect:/account";
    }
    @GetMapping("/add-driver-info")
    public String getAddDriverDescriptionPage(Model model) {
        User currentUser = userService.getCurrentUser();
        Set<Role> roles = currentUser.getRoles();
        if(roles.stream().noneMatch(role -> role.getName().equals("ROLE_DRIVER"))) {
            model.addAttribute("justBecameDriver", true);
            roles.add(userService.getRoleByName("ROLE_DRIVER"));
        } else {
            model.addAttribute("justBecameDriver", false);
        }
        if(currentUser.getDriverDescription() == null) {
            currentUser.setDriverDescription(new DriverDescription());
        }
        model.addAttribute("driverDescription", currentUser.getDriverDescription());
        Map<Long, String> carsDroplist = carService.allCarsSorted().stream().collect(Collectors.toMap(Car::getId,
                car -> String.format("%s %s (%s - %s)", car.getManufacturer(), car.getModel(),
                        car.getYearStartOfManufacturing() == null ? "неизв." : car.getYearStartOfManufacturing(),
                        car.getYearEndOfManufacturing() == null ? "сегодня" : car.getYearEndOfManufacturing())));
        model.addAttribute("cars", carsDroplist.entrySet());
        return "account/add-driver-info";
    }
}