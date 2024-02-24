package com.proiectis.masinifa.controller;

import com.proiectis.masinifa.entitati.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        UserAccount user = new UserAccount(); // Se creeaza un obiect UserAccount pentru a fi utilizat in formularul de login

        model.addAttribute("user", user); // Se adauga obiectul UserAccount in model pentru a fi utilizat in sablonul "login"

        return "login";
    }

}
