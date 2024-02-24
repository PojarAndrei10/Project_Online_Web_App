package com.proiectis.masinifa.controller;

import com.proiectis.masinifa.entitati.Car;
import com.proiectis.masinifa.entitati.TestDrive;
import com.proiectis.masinifa.servicii.UserCarService;
import com.proiectis.masinifa.servicii.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserCarController {

    @Autowired
    private UserCarService userCarService;

    @Autowired
    private UserService userService;

    // Pagina masinilor postate de utilizator
    @GetMapping("/my-posted-car")
    public String myPostedCarPage(Model model) {
        List<Car> userCar = userCarService.listUserCar();

        model.addAttribute("userCar", userCar);

        return "user/my-posted-car";
    }

    // Postare masina
    @GetMapping("/post-car")
    public String postCar(Model model) {
        Car car = new Car();

        model.addAttribute("car", car);

        return "user/post-car";
    }

    @PostMapping("/postCarProcess")
    public String postCarProcess(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult,
                                 @RequestParam("imageFile") MultipartFile file, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/post-car";
        }

        if (file.isEmpty()) {
            model.addAttribute("fileError", "Poza cu masina este necesara");
            return "user/post-car";
        }

        try {
            userCarService.postCar(file, car);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return "redirect:/user/my-posted-car";
    }

    // Editare masina postata
    @GetMapping("/edit-posted-car")
    public String editPostedCar(@RequestParam("id") int id, Model model) {
        List<Car> userCars = userCarService.listUserCar();

        // Verificare daca utilizatorul care acceseaza linkul este proprietarul masinii postate
        for (Car car : userCars) {
            if (car.getIdCar() == id) {
                model.addAttribute("car", car);
                return "user/edit-posted-car";
            }
        }
        return "redirect:/user/my-posted-car";
    }

    @PostMapping("/editCarProcess")
    public String saveEditCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/edit-posted-car";
        }

        userCarService.editCar(car);

        return "redirect:/cars/" + car.getMake() + "/" + car.getModel() + "/" + car.getYear() + "/" + car.getIdCar();
    }

    // Activare si dezactivare masina postata
    @GetMapping("/activate/{idCar}")
    public String activatePostedCar(@PathVariable("idCar") int id) {
        userCarService.activateCarPost(id);
        return "redirect:/user/my-posted-car";
    }

    @GetMapping("/deactivate/{idCar}")
    public String deactivatePostedCar(@PathVariable("idCar") int id) {
        userCarService.deactivateCarPost(id);
        return "redirect:/user/my-posted-car";
    }

    // Listare test drive-uri
    @GetMapping("/test-drive")
    public String listTestDrive(Model model) {
        List<TestDrive> listTestDrive = userCarService.listTestDrive();
        model.addAttribute("listTestDrive", listTestDrive);
        return "user/list-test-drive";
    }

    // Incarcare imagine masina
    @GetMapping("/upload-car-picture")
    public String uploadPicture(@RequestParam("idCar") int idCar, HttpSession session) {
        session.setAttribute("idCar", idCar);
        return "user/upload-car-picture";
    }

    @PostMapping("/uploadCarPicture")
    public String uploadCarImage(@RequestParam("imageFile") MultipartFile imageFile, Model model, HttpSession session) {
        String type = imageFile.getContentType();

        if (type != null && (type.equals("image/jpg") || type.equals("image/jpeg") || type.equals("image/png"))) {
            Car car = userCarService.getCarById((int) session.getAttribute("idCar"));

            try {
                userCarService.saveUploadPicture(imageFile, car);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "redirect:/cars";
        }

        model.addAttribute("message", "Tipul de fisier nu este suportat");
        return "user/upload-car-picture";
    }

}
