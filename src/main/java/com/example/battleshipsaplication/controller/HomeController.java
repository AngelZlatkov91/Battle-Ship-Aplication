package com.example.battleshipsaplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String loggedOutIndex(){
        return "index";
    }

    @GetMapping ("/home")
    public String loggedInIndex(){
          return "index";
    }
}
