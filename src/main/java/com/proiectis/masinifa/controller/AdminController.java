package com.proiectis.masinifa.controller;

import com.proiectis.masinifa.entitati.Car;
import com.proiectis.masinifa.entitati.CarBidding;
import com.proiectis.masinifa.entitati.UserAccount;
import com.proiectis.masinifa.entitati.UserProfile;
import com.proiectis.masinifa.servicii.AdminService;
import com.proiectis.masinifa.servicii.UserCarService;
import com.proiectis.masinifa.servicii.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserCarService userCarService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String admin() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        // Obtinerea listelor de utilizatori si admini din serviciul de admin
        List<UserAccount> listUser = adminService.listUser();
        List<UserAccount> listAdmin = adminService.listAdmin();

        model.addAttribute("listUser", listUser);
        model.addAttribute("listAdmin", listAdmin);

        // Obtinerea profilului utilizatorului logat si setarea acestuia in sesiune
        UserAccount user = userService.getUserLogin();
        UserProfile profile = user.getProfile();
        session.setAttribute("profileLog", profile);

        return "admin/dashboard";
    }

    // Editare utilizator
    @GetMapping("/edit-user")
    public String editUser(@RequestParam("id") int id, Model model) {
        // Obtinerea profilului utilizatorului pentru editare
        UserProfile profile = adminService.getProfileById(id);

        model.addAttribute("profile", profile);

        return "admin/edit-user";
    }

    @PostMapping("/editProfileProcess")
    public String saveEditUser(@Valid @ModelAttribute("profile") UserProfile profile, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/edit-user";
        }

        // Salvarea modificarilor facute asupra profilului utilizatorului
        adminService.editUser(profile);

        return "redirect:/admin/dashboard";
    }

    // Marcare utilizator ca admin
    @GetMapping("/mark-admin/{idUser}")
    public String markAdmin(@PathVariable("idUser") int id) {
        adminService.markAsAdmin(id);

        return "redirect:/admin/dashboard";
    }

    // Management masini
    @GetMapping("/car-management")
    public String carManagement(Model model) {
        // Obtinerea listelor de masini si licitatii din serviciul de admin
        List<Car> listCar = adminService.listCar();
        List<CarBidding> listCarBid = adminService.listCarBid();

        model.addAttribute("listCar", listCar);
        model.addAttribute("listCarBid", listCarBid);

        return "admin/car-management";
    }

    // Dezactivare masina
    @GetMapping("/deactivate/{idCar}")
    public String deactivateCarPost(@PathVariable("idCar") int id) {
        userCarService.deactivateCarPost(id);

        return "redirect:/admin/car-management";
    }

    // Activare masina
    @GetMapping("/activate/{idCar}")
    public String activateCarPost(@PathVariable("idCar") int id) {
        userCarService.activateCarPost(id);

        return "redirect:/admin/car-management";
    }

    // Aprobare licitatie masina
    @GetMapping("/approve-bid/{idBid}")
    public String approveBidCarPost(@PathVariable("idBid") int id) {
        adminService.approveCarBid(id);

        return "redirect:/admin/car-management";
    }

    // Respingere licitatie masina
    @GetMapping("/deny-bid/{idBid}")
    public String denyBidCarPost(@PathVariable("idBid") int id) {
        adminService.denyCarBid(id);

        return "redirect:/admin/car-management";
    }
}
