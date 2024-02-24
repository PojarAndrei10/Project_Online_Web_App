package com.proiectis.masinifa.servicii;

import com.proiectis.masinifa.entitati.*;
import com.proiectis.masinifa.repository.CarBiddingRepository;
import com.proiectis.masinifa.repository.CarRepository;
import com.proiectis.masinifa.repository.UserProfileRepository;
import com.proiectis.masinifa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepo; // Repository pentru operatiile legate de utilizatori

    @Autowired
    private UserProfileRepository userProfileRepo; // Repository pentru operatiile legate de profilele utilizatorilor

    @Autowired
    private CarRepository carRepo; // Repository pentru operatiile legate de masini

    @Autowired
    private CarBiddingRepository carBidRepo; // Repository pentru operatiile legate de licitatiile masinilor

    @Override
    public void editUser(UserProfile profile) {
        // Obtine profilul utilizatorului bazat pe ID-ul profilului specificat
        UserProfile editedProfile = userProfileRepo.findById(profile.getIdProfile()).get();

        // Actualizeaza detaliile profilului cu informatiile noi
        editedProfile.setFirstName(profile.getFirstName());
        editedProfile.setLastName(profile.getLastName());
        editedProfile.setPhoneNumber(profile.getPhoneNumber());
        editedProfile.setAddress(profile.getAddress());
        editedProfile.setAbout(profile.getAbout());

        // Salveaza modificarile in baza de date
        userProfileRepo.save(editedProfile);
    }

    @Override
    public void markAsAdmin(int idUser) {
        UserAccount user = userRepo.findById(idUser).get();

        // Seteaza rolul utilizatorului ca "ROLE_ADMIN"
        Role role = new Role();
        role.setRole("ROLE_ADMIN");
        role.setUser(user);

        // Seteaza lista de roluri a utilizatorului si rolul specific "ADMIN"
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        user.setRole("ADMIN");

        userRepo.save(user);
    }

    @Override
    public UserProfile getProfileById(int idProfile) {
        // Obtine profilul utilizatorului bazat pe ID-ul profilului specificat
        UserProfile profile = userProfileRepo.findById(idProfile).get();

        return profile;
    }

    @Override
    public List<UserAccount> listUser() {
        // Obtine o lista de toti utilizatorii din sistem
        List<UserAccount> listUser = userRepo.findAll();

        // Elimina utilizatorii care au rolul "ADMIN" din lista returnata
        listUser.removeIf(user -> user.getRole().equals("ADMIN"));

        return listUser;
    }

    @Override
    public List<UserAccount> listAdmin() {
        // Obtine o lista de toti utilizatorii din sistem
        List<UserAccount> listAdmin = userRepo.findAll();

        // Elimina utilizatorii care au rolul "USER" din lista returnata
        listAdmin.removeIf(admin -> admin.getRole().equals("USER"));

        return listAdmin;
    }

    @Override
    public List<Car> listCar() {
        // Obtine o lista de toate masinile din sistem
        List<Car> listCar = carRepo.findAll();

        return listCar;
    }

    @Override
    public List<CarBidding> listCarBid() {
        // Obtine o lista de toate licitatiile pentru masini din sistem
        List<CarBidding> listCarBid = carBidRepo.findAll();

        // Elimina licitatiile care au statusul "PRIMU_PRET" din lista returnata
        listCarBid.removeIf(bid -> bid.getStatus().equals("PRIMU_PRET"));

        return listCarBid;
    }

    @Override
    public void approveCarBid(int idBid) {
        // Obtine licitatia pentru masina pe baza ID-ului specificat
        CarBidding carBidding = carBidRepo.findById(idBid).get();

        // Seteaza statusul licitatiei ca "APROBAT"
        carBidding.setStatus("APROBAT");

        // Seteaza statusul masinii asociate licitatiei ca "VANDUT"
        carBidding.getCar().setStatus("VANDUT");

        carBidRepo.save(carBidding);
    }

    @Override
    public void denyCarBid(int idBid) {
        // Obtine licitatia pentru masina pe baza ID-ului specificat
        CarBidding carBidding = carBidRepo.findById(idBid).get();

        // Seteaza statusul licitatiei ca "REFUZAT"
        carBidding.setStatus("REFUZAT");

        carBidRepo.save(carBidding);
    }

}