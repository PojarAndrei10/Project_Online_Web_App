package com.proiectis.masinifa.repository;

import com.proiectis.masinifa.entitati.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Integer> {
    // Aceasta interfata extinde JpaRepository, care ofera metode predefinite pentru operatii CRUD
    // si interactiuni cu baza de date pentru entitatea UserAccount, utilizand un tip de ID de tip Integer.

    // Aceasta va returna un obiect UserAccount care are username-ul specificat.
    UserAccount findByUsername(String username);
    // Prin utilizarea numelui metodei conform conventiei Spring Data JPA, acesta va efectua automat
    // interogarea pentru a cauta in baza de date dupa coloana "username" din entitatea UserAccount.
}
