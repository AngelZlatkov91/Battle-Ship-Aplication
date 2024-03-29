package com.example.battleshipsaplication.controller;

import com.example.battleshipsaplication.model.dto.StartBattleDTO;
import com.example.battleshipsaplication.service.BattleService;
import com.example.battleshipsaplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BattleController {

    private final BattleService battleService;

    private final UserService userService;

    @Autowired
    public BattleController(BattleService battleService, UserService userService) {
        this.battleService = battleService;
        this.userService = userService;
    }

    @PostMapping("/battle")
    public String battle(@Valid StartBattleDTO startBattleDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if (this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("startBattleDTO",startBattleDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.startBattleDTO",bindingResult);
            return "redirect:/home";
        }

        this.battleService.attack(startBattleDTO);
        return "redirect:/home";
    }
}
