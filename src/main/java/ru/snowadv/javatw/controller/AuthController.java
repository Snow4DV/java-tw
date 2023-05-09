package ru.snowadv.javatw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.snowadv.javatw.entity.User;
import ru.snowadv.javatw.service.UserService;

import javax.servlet.ServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;

    @GetMapping("register")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("register")
    public String addUser(User newUser, Model model) {

        if (!newUser.getPassword().equals(newUser.getPasswordConfirm())){
            model.addAttribute("error", "Введённые пароли не совпадают");
            model.addAttribute("newUser", new User());
            return "register";
        }
        if (!userService.saveUser(newUser, "ROLE_USER")){
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            model.addAttribute("newUser", new User());
            return "register";
        }

        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(ServletRequest request, Model model) {
        model.addAttribute("loginError", request.getParameterMap().containsKey("error"));
        return "authorize";
    }
}