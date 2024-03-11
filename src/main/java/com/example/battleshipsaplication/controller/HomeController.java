package com.example.battleshipsaplication.controller;

import com.example.battleshipsaplication.model.dto.ShipDTO;
import com.example.battleshipsaplication.service.ShipService;
import com.example.battleshipsaplication.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
     private final ShipService shipService;

     private final LoggedUser loggedUser;

     @Autowired
    public HomeController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
         this.loggedUser = loggedUser;
     }

    @GetMapping("/")
    public String loggedOutIndex(){
        return "index";
    }

    @GetMapping ("/home")
    public String loggedInIndex(Model model){
        long id = this.loggedUser.getId();
        List<ShipDTO> ownShips =this.shipService.getShipsOwnedBy(id);
         List<ShipDTO> enemyShips =this.shipService.getShipsNotOwnedBy(id);
        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);

          return "home";
    }
}

