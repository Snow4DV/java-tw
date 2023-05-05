package ru.snowadv.javatw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.snowadv.javatw.service.UserService;

import javax.servlet.ServletRequest;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ContentController {


    private final UserService userService;

    @GetMapping(value={"", "/", "/index", "/index.html", "/index.htm"})
    public String showMainPage(ServletRequest request, Model model) {
        model.addAttribute("isDriver", request.getParameterMap().containsKey("driver"));
        return "index";
    }




}