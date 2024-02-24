package com.proiectis.masinifa.entitati;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "tb_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private int idCar;

    @NotBlank(message = "Marca este necesara")
    @Column(nullable = false)
    private String make;

    @NotBlank(message = "Model este necesar")
    @Column(nullable = false)
    private String model;

    @NotEmpty(message = "Anul este necesar")
    @Size(min = 4, max = 4, message = "Anul trebuie sa aiba o lungime de 4 caractere")
    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @Digits(integer = 10, fraction = 2)
    @Positive(message = "Pretul nu poate fi mai mic ca 1")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserAccount user; // Utilizatorul care detine masina

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "car")
    private CarPicture carPicture; // Imaginea masinii

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
    private List<CarBidding> carBiddings; // Lista de oferte pentru masina

    public Car() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public CarPicture getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(CarPicture carPicture) {
        this.carPicture = carPicture;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public List<CarBidding> getCarBiddings() {
        return carBiddings;
    }

    public void setCarBiddings(List<CarBidding> carBiddings) {
        this.carBiddings = carBiddings;
    }

}