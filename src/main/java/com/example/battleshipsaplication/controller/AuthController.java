package com.example.battleshipsaplication.controller;

import com.example.battleshipsaplication.model.dto.LoginDTO;
import com.example.battleshipsaplication.model.dto.UserRegistrationDTO;
import com.example.battleshipsaplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initRegistrationDTO(){
        return new UserRegistrationDTO();
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO () {
        return new LoginDTO();
    }
    @GetMapping("/register")
    public String register() {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
          if (bindingResult.hasErrors() || !this.userService.register(userRegistrationDTO)) {
              redirectAttributes.addFlashAttribute("userRegistrationDTO",userRegistrationDTO);
              redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO",bindingResult);
              return "redirect:/register";
          }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login (@Valid LoginDTO loginDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO",loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO",bindingResult);
            return "redirect:/login";
        }
        if (!this.userService.login(loginDTO)) {
            redirectAttributes.addFlashAttribute("loginDTO",loginDTO);
            redirectAttributes.addFlashAttribute("badCredentials",true);

            return "redirect:/login";
        }

      return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(){
        this.userService.logout();
        return "redirect:/";
    }




}
