package com.proiectis.masinifa.controller;

import com.proiectis.masinifa.entitati.Car;
import com.proiectis.masinifa.entitati.CarBidding;
import com.proiectis.masinifa.entitati.TestDrive;
import com.proiectis.masinifa.entitati.UserAccount;
import com.proiectis.masinifa.servicii.UserCarService;
import com.proiectis.masinifa.servicii.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CarSalesController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCarService userCarService;

    // Licitatie pentru masina
    @GetMapping("/car-bid")
    public String postBidding(@RequestParam("id") int id, Model model, HttpSession session) {
        Car car = userCarService.getCarById(id);
        UserAccount user = userService.getUserLogin();

        CarBidding carBidding = new CarBidding();
        int highestBidding = userCarService.highestBidding(id);

        model.addAttribute("user", user);
        model.addAttribute("car", car);
        model.addAttribute("carBidding", carBidding);
        session.setAttribute("highestBidding", highestBidding);

        return "user/car-bid";
    }

    @PostMapping("/postCarBidding")
    public String saveCarBidding(@ModelAttribute("carBidding") CarBidding carBidding,
                                 @RequestParam("highestBidding") int highestBidding, Model model, HttpSession session) {

        Car car = carBidding.getCar();

        if (carBidding.getBidPrice() <= highestBidding || carBidding.getBidPrice() <= car.getPrice()) {
            int highestBid = (int) session.getAttribute("highestBidding");
            model.addAttribute("highestBidding", highestBid);
            model.addAttribute("carBidding", carBidding);
            model.addAttribute("car", car);
            model.addAttribute("message", "Pretul licitatiei nu poate fi mai mic sau egal cu pretul oferit deja");
            return "user/car-bid";
        }

        userCarService.postCarBidding(carBidding);
        session.removeAttribute("highestBidding");
        return "redirect:/cars/" + car.getMake() + "/" + car.getModel() + "/" + car.getYear() + "/" + car.getIdCar();
    }

    // Programare test-drive
    @GetMapping("/test-drive/{idCar}")
    public String testDrive(@PathVariable("idCar") int idCar, Model model) {
        TestDrive testDrive = new TestDrive();
        UserAccount user = userService.getUserLogin();
        Car car = userCarService.getCarById(idCar);

        model.addAttribute("testDrive", testDrive);
        model.addAttribute("user", user);
        model.addAttribute("car", car);

        return "user/test-drive";
    }

    @PostMapping("/test-drive/testDriveProcess")
    public String saveTestDrive(@ModelAttribute("testDrive") TestDrive testDrive) {
        userCarService.saveTestDrive(testDrive.getDate(), testDrive.getUser(), testDrive.getCar());
        return "redirect:/";
    }
}
