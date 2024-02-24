package com.proiectis.masinifa.controller;

import com.proiectis.masinifa.entitati.Car;
import com.proiectis.masinifa.entitati.UserProfile;
import com.proiectis.masinifa.servicii.CarService;
import com.proiectis.masinifa.servicii.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class masiniFAController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Car> listCar = carService.featuredCars();

        model.addAttribute("listCar", listCar);
        return "home";
    }

    // Pagina de contact
    @GetMapping("/contact-us")
    public String contactUs() {
        return "contact-us";
    }

    // Despre noi
    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us"; // Returneaza sablonul "about-us" pentru afisarea paginii Despre noi
    }

    // Vizualizare utilizator
    @GetMapping("/view-user/{firstName}/{idProfile}")
    public String viewUser(@PathVariable("idProfile") int idProfile, Model model) {
        UserProfile profile = userService.getProfile(idProfile);
        List<Car> listCar = carService.listCar();

        // Filtrare masini pentru afisarea doar a celor asociate cu profilul utilizatorului specificat
        listCar.removeIf(car -> car.getUser().getProfile().getIdProfile() != idProfile);
        listCar.removeIf(car -> car.getStatus().equals("DEZACTIVAT"));

        model.addAttribute("listCar", listCar);
        model.addAttribute("profile", profile);

        return "view-user";
    }
}
