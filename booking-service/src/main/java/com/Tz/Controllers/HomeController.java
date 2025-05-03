package com.Tz.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    //http://localhost:5005/
    @GetMapping
    public String home() {
        return "Welcome to the Booking MicroService!";
    }
}
