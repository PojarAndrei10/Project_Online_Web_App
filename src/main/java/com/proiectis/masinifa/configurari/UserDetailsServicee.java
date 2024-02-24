package com.proiectis.masinifa.configurari;

import com.proiectis.masinifa.entitati.UserAccount;
import com.proiectis.masinifa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Anotarea @Service indica ca aceasta clasa este un bean Spring si va fi gestionata de containerul Spring.
@Service
public class UserDetailsServicee implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Folosim UserRepository pentru a cauta un UserAccount dupa numele de utilizator furnizat
        UserAccount user = userRepo.findByUsername(username);

        // Daca nu gasim un utilizator, aruncam o exceptie specifica
        if (user == null) {
            throw new UsernameNotFoundException("Invalid user sau parola");
        }

        // Returnam un obiect UserDetails personalizat pe baza UserAccount gasit
        return new UserDetailss(user);
    }
}