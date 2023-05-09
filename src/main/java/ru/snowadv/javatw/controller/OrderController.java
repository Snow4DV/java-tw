package ru.snowadv.javatw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.snowadv.javatw.entity.*;
import ru.snowadv.javatw.service.CarService;
import ru.snowadv.javatw.service.OfferService;
import ru.snowadv.javatw.service.OrderService;
import ru.snowadv.javatw.service.UserService;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {


    private final UserService userService;
    private final CarService carService;
    private final OrderService orderService;
    public final OfferService offerService;

    @GetMapping
    public String getOrdersPage(Model model) {
        User currentUser = userService.getCurrentUser();
        boolean driver = currentUser.isDriver();
        if(driver && orderService.getAssignedUnfinishedOrderByDriver(currentUser.getDriverDescription()) != null) {
            return "redirect:/order/current";
        }
        model.addAttribute("isDriver", driver);
        model.addAttribute("orders", driver ? orderService.getUnassignedOrders() :
                orderService.getActiveOrdersByUser(currentUser));
        return "order/index";
    }

    @GetMapping("/archive")
    public String getArchiveOrdersPage(Model model) {
        User currentUser = userService.getCurrentUser();
        boolean driver = currentUser.isDriver();
        model.addAttribute("isDriver", driver);
        model.addAttribute("orders", driver ? orderService.getAllDoneByDriver(currentUser.getDriverDescription()) :
                orderService.getAllDoneByUser(currentUser));
        return "order/archive";
    }

    @GetMapping("/add")
    public String getAddOrderPage(Model model) {
        model.addAttribute("newOrder", new Order());
        return "order/add";
    }
    @PostMapping("/add")
    public String addOrder(Order order) {
        order.setUser(userService.getCurrentUser());
        orderService.save(order);
        User currentUser = userService.getCurrentUser();
        currentUser.addOrder(order);
        userService.save(currentUser);
        return "redirect:/order";
    }

    @GetMapping("/{id}/choose")
    public String chooseDriver(@PathVariable Long id, @RequestParam Long offerId) {
        Offer offer = offerService.getOfferById(offerId);
        Order order = orderService.getById(id);
        order.setAssignedDriver(offer.getDriver());
        order.setPrice(offer.getPrice());
        orderService.save(order);
        return "redirect:/order/" + id + "/info";
    }

    @GetMapping("/{id}/giveRating")
    public String giveRating(@PathVariable Long id, @RequestParam int rating) {
        Order order = orderService.getById(id);
        DriverDescription assignedDriver = order.getAssignedDriver();
        if(assignedDriver == null) {
            return "redirect:/order";
        }
        assignedDriver.addRating(rating);
        order.setLeftRating(rating);
        orderService.save(order);
        userService.save(assignedDriver.getUser());
        return "redirect:/order/" + id + "/info";
    }



    @GetMapping("/{id}/finish")
    public String finishOrder(@PathVariable Long id) {
        Order order = orderService.getById(id);
        order.setDone(true);
        orderService.save(order);
        return "redirect:/order" ;
    }


    @GetMapping("/current")
    public String getCurrentOrderPage(Model model) {
        Order order;
        User currentUser = userService.getCurrentUser();
        if(currentUser.isDriver()) {
            DriverDescription driver = currentUser.getDriverDescription();
            order = orderService.getAssignedUnfinishedOrderByDriver(driver);
        } else {
            order = orderService.getFirstUnfinishedOrderByUser(currentUser);
        }

        if(order == null) {
            return "redirect:/order";
        }
        model.addAttribute("order", order);
        model.addAttribute("offers", order.getOffers());
        model.addAttribute("isDriver", currentUser.isDriver());
        return "order/info";
    }

    @GetMapping("/{id}/offer")
    public String offerDriver(@PathVariable Long id, @RequestParam Integer price) {
        Order order = orderService.getById(id);
        DriverDescription driver = userService.getCurrentUser().getDriverDescription();
        Offer offer = new Offer(order, driver, price);
        order.addOffer(offer);
        offerService.save(offer);
        orderService.save(order);
        userService.save(userService.getCurrentUser());
        return "redirect:/order";
    }


    @GetMapping("/{id}/info")
    public String getInfoPage(@PathVariable Long id, Model model) {
        Order order = orderService.getById(id);
        model.addAttribute("order", order);
        model.addAttribute("offers", order.getOffers());
        model.addAttribute("isDriver", false);
        return "order/info";
    }

}