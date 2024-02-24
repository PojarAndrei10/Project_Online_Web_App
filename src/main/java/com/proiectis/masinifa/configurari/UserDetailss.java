package com.proiectis.masinifa.configurari;

import com.proiectis.masinifa.entitati.Role;
import com.proiectis.masinifa.entitati.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailss implements UserDetails {

    private final UserAccount user;

    public UserDetailss(UserAccount user) {
        super(); // Apelarea constructorului clasei parinte
        this.user = user; // Initializarea variabilei membre user cu valoarea primita in constructor
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // Suprascrierea metodei din interfata UserDetails pentru a obtine autoritatile utilizatorului
        List<Role> roles = user.getRoles(); // Se obtin rolurile asociate utilizatorului

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) { // Se itereaza prin fiecare rol
            authorities.add(new SimpleGrantedAuthority(role.getRole())); // Se adauga fiecare rol ca o autoritate (GrantedAuthority)
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { // Suprascrierea metodei pentru a verifica daca contul utilizatorului nu a expirat
        return true; // Se returneaza mereu true, presupunand ca conturile nu expira
    }

    @Override
    public boolean isAccountNonLocked() { // Suprascrierea metodei pentru a verifica daca contul utilizatorului nu este blocat
        return true; // Se returneaza mereu true, presupunand ca conturile nu sunt blocate
    }

    @Override
    public boolean isCredentialsNonExpired() { // Suprascrierea metodei pentru a verifica daca credentialele utilizatorului nu au expirat
        return true; // Se returneaza mereu true, presupunand ca credentialele nu expira
    }

    @Override
    public boolean isEnabled() { // Suprascrierea metodei pentru a verifica daca utilizatorul este activat
        return true; // Se returneaza mereu true, presupunand ca utilizatorii sunt activati
    }

    public UserAccount getUser() { // Metoda pentru a obtine obiectul UserAccount asociat acestui obiect UserDetailss
        return user;
    }

}
