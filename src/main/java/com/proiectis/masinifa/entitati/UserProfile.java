package com.proiectis.masinifa.entitati;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profile")
    private int idProfile;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "Prenumele este obligatoriu")
    @Size(min = 1, max = 35, message = "Prenumele trebuie sa aiba intre 1 si 35 caractere")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "Numele este obligatoriu")
    @Size(min = 1, max = 35, message = "Numele trebuie sa aiba intre 1 si 35 caractere")
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    @NotEmpty(message = "Numarul de telefon este obligatoriu")
    private String phoneNumber;

    @NotEmpty(message = "Adresa este obligatorie")
    @Column(nullable = false)
    private String address;

    private String about;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserAccount user; // Legatura catre contul de utilizator asociat acestui profil

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "profile")
    private ProfilePicture profilePicture; // Legatura catre imaginea de profil asociata acestui profil de utilizator

    public UserProfile() {
    }

    public UserProfile(String firstName, String lastName, String phoneNumber, String address,
                       String about, UserAccount user, ProfilePicture profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.about = about;
        this.user = user;
        this.profilePicture = profilePicture;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

}