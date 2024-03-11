package com.example.battleshipsaplication.controller;

import com.example.battleshipsaplication.model.dto.CreateShipDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {

    @GetMapping("/ships")
    public String ships(){
        return "ship-add";
    }

    @PostMapping("/ships")
    public String ships(@Valid CreateShipDTO createShipDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {


    }
}
