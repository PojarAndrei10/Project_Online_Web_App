package com.proiectis.masinifa.controller;

import com.proiectis.masinifa.entitati.UserAccount;
import com.proiectis.masinifa.entitati.UserProfile;
import com.proiectis.masinifa.servicii.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String register() {
        return "redirect:/register/account";
    }

    // Inregistrare cont
    @GetMapping("/account")
    public String registerAccount(Model model) {
        UserAccount user = new UserAccount();

        model.addAttribute("account", user);

        return "register-account";
    }

    @PostMapping("/accountProcess")
    public String registerAccountProcess(
            @Valid @ModelAttribute("account") UserAccount user,
            BindingResult bindingResult, HttpSession session, Model model) {

        if (bindingResult.hasErrors()) {
            return "register-account";
        }

        UserAccount existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null) {
            model.addAttribute("message", "Numele de utilizator exista deja in baza de date. Va rugam sa alegeti altul.");
            return "register-account";
        }

        session.setAttribute("registerAccount", user);

        return "redirect:/register/profile";
    }

    // Inregistrare profil
    @GetMapping("/profile")
    public String registerProfile(Model model) {
        UserProfile profile = new UserProfile();

        model.addAttribute("profile", profile);

        return "register-profile";
    }

    @PostMapping("/profileProcess")
    public String registerProfileProcess(
            @Valid @ModelAttribute("profile") UserProfile profile,
            BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "register-profile";
        }

        UserAccount user = (UserAccount) session.getAttribute("registerAccount");

        userService.saveUser(user, profile); // Salvare utilizator si profil

        return "redirect:/register/thank-you";
    }

    // Pagina multumiri
    @GetMapping("thank-you")
    public String thankYou(HttpSession session) {
        session.invalidate(); // Invalidare sesiune

        return "thank-you";
    }
}
