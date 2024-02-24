package com.proiectis.masinifa.controller;

import com.proiectis.masinifa.entitati.Car;
import com.proiectis.masinifa.servicii.CarService;
import com.proiectis.masinifa.servicii.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserCarService userCarService;

    @GetMapping("")
    public String carPage(Model model) {
        // Obtinerea listei de masini
        List<Car> listCar = carService.listCar();

        model.addAttribute("listCar", listCar);

        return "cars"; // Sablonului "cars" pentru afisarea listei de masini
    }

    // Detalii masina
    @GetMapping("/{make}/{model}/{year}/{id_car}")
    public String carDetails(@PathVariable("id_car") int id, Model model) {
        // Obtinerea informatiilor despre masina cu ID-ul specificat
        Car car = carService.getCarById(id);

        // Obtinerea celei mai mari licitatii pentru masina respectiva
        int highestBidding = userCarService.highestBidding(id);

        // Adaugarea informatiilor pentru a fi afisate in pagina de detalii a masinii
        model.addAttribute("car", car);
        model.addAttribute("highestBidding", highestBidding);

        return "car-details";
    }

    // Cautare masina dupa cuvant cheie
    @GetMapping(value = "", params = "keyword")
    public String searchCar(@RequestParam("keyword") String keyword, Model model) {
        List<Car> searchCar = carService.searchCar(keyword);

        model.addAttribute("listCar", searchCar);
        return "cars";
    }

    // Cautare masina intr-un anumit interval de preturi
    @GetMapping(value = "", params = {"low", "high"})
    public String searchCarByPriceRange(@RequestParam("low") int low, @RequestParam("high") int high, Model model) {
        List<Car> searchCar = carService.searchCarByPriceRange(low, high);

        model.addAttribute("listCar", searchCar);
        return "cars";
    }
}
