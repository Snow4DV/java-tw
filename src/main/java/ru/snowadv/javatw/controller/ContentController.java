package ru.snowadv.javatw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.snowadv.javatw.service.UserService;

import javax.servlet.ServletRequest;
import java.security.Principal;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ContentController {


    private final UserService userService;

    @GetMapping(value={"", "/", "/index", "/index.html", "/index.htm"})
    public String showMainPage(Principal principal, ServletRequest request, Model model) {
        if(principal != null) {
            return "redirect:/order";
        }
        model.addAttribute("isDriver", request.getParameterMap().containsKey("driver"));
        return "index";
    }




}