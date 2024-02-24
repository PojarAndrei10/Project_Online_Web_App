package com.proiectis.masinifa.entitati;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int idUser;

    @NotEmpty(message = "User este necesar")
    @Size(min = 3, max = 15, message = "User trebuie sa aiba intre 3 si 15 caractere")
    @Column(nullable = false)
    private String username;

    @NotEmpty(message = "Parola este necesara")
    @Size(min = 6, message = "Parola trebuie sa contina minim 6 caractere")
    @Column(nullable = false)
    private String password;

    private String role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private UserProfile profile; // Profilul asociat utilizatorului

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Role> roles; // Lista de roluri asociate utilizatorului

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Car> cars; // Lista de masini asociate utilizatorului

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<CarBidding> carBiddings; // Lista de licitatii pentru masini asociate utilizatorului

    public UserAccount() {
    }

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<CarBidding> getCarBiddings() {
        return carBiddings;
    }

    public void setCarBiddings(List<CarBidding> carBiddings) {
        this.carBiddings = carBiddings;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}