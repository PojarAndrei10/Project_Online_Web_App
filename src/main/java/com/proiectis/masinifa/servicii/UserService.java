package com.proiectis.masinifa.servicii;

import com.proiectis.masinifa.entitati.UserAccount;
import com.proiectis.masinifa.entitati.UserProfile;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    void saveUser(UserAccount user, UserProfile profile);

    UserAccount findByUsername(String username);

    UserAccount getUserLogin();

    void saveImage(MultipartFile file, UserProfile profile) throws Exception;

    void editUserProfile(UserProfile profile);

    UserProfile getProfile(int idProfile);
}