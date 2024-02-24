package com.proiectis.masinifa.servicii;

import com.proiectis.masinifa.entitati.ProfilePicture;
import com.proiectis.masinifa.entitati.Role;
import com.proiectis.masinifa.entitati.UserAccount;
import com.proiectis.masinifa.entitati.UserProfile;
import com.proiectis.masinifa.repository.ProfilePictureRepository;
import com.proiectis.masinifa.repository.UserProfileRepository;
import com.proiectis.masinifa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserProfileRepository userProfileRepo;

    @Autowired
    private ProfilePictureRepository profilePictureRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserAccount user, UserProfile profile) {
        // Creati un nou obiect UserAccount pentru a salva in baza de date
        UserAccount saveUser = new UserAccount();

        // Initializati o lista de roluri si un obiect de rol pentru utilizator
        List<Role> roles = new ArrayList<>();
        Role role = new Role();

        // Setarea numelui de utilizator
        saveUser.setUsername(user.getUsername());
        saveUser.setPassword(passwordEncoder.encode(user.getPassword()));

        // Adaugarea rolului "ROLE_USER" pentru utilizator si legarea acestuia de rol
        roles.add(role);
        for (Role r : roles) {
            r.setRole("ROLE_USER");
            r.setUser(saveUser);
        }

        // Setarea profilului utilizatorului si a rolurilor
        profile.setUser(saveUser);
        saveUser.setProfile(profile);
        saveUser.setRoles(roles);
        saveUser.setRole("USER");

        userRepo.save(saveUser);
    }

    @Override
    public UserAccount findByUsername(String username) {
        // Cauta un cont de utilizator in baza de date dupa numele de utilizator
        UserAccount user = userRepo.findByUsername(username);

        return user;
    }

    @Override
    public UserAccount getUserLogin() {
        // Obtine autentificarea utilizatorului curent
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtine numele de utilizator al utilizatorului autentificat
        String username = authentication.getName();

        // Gaseste contul de utilizator asociat numelui de utilizator
        UserAccount user = userRepo.findByUsername(username);

        return user;
    }

    @Override
    public void saveImage(MultipartFile file, UserProfile profile) throws Exception {
        try {
            // Verifica daca profilul nu are o imagine de profil asociata
            if (profile.getProfilePicture() == null) {
                // Seteaza imaginea de profil in cazul in care nu exista o imagine asociata
                ProfilePicture picture = new ProfilePicture();

                picture.setFileName(file.getOriginalFilename());
                picture.setFileType(file.getContentType());
                picture.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
                picture.setProfile(profile);

                profilePictureRepo.save(picture);

            } else {
                // Editeaza imaginea de profil daca exista deja o imagine asociata profilului
                ProfilePicture picture = profile.getProfilePicture();

                picture.setFileName(file.getOriginalFilename());
                picture.setFileType(file.getContentType());
                picture.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
                picture.setProfile(profile);

                profilePictureRepo.save(picture);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void editUserProfile(UserProfile profile) {
        // Obtine profilul utilizatorului care trebuie editat
        UserProfile editedProfile = userProfileRepo.findById(profile.getIdProfile()).get();

        // Actualizeaza informatiile profilului cu noile date furnizate
        editedProfile.setFirstName(profile.getFirstName());
        editedProfile.setLastName(profile.getLastName());
        editedProfile.setPhoneNumber(profile.getPhoneNumber());
        editedProfile.setAddress(profile.getAddress());
        editedProfile.setAbout(profile.getAbout());

        userProfileRepo.save(editedProfile);
    }

    @Override
    public UserProfile getProfile(int idProfile) {
        // Gaseste si returneaza profilul utilizatorului cu ID-ul specificat
        UserProfile profile = userProfileRepo.findById(idProfile).get();

        return profile;
    }


}