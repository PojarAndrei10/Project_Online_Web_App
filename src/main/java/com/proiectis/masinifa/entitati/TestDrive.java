package com.proiectis.masinifa.entitati;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "tb_test_drive")
public class TestDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_test_drive")
    private int idTestDrive;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(nullable = false)
    private String date; // Data programarii testului

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserAccount user; // Utilizatorul care a programat testul

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car")
    private Car car; // Masina pentru care a fost programat testul

    public TestDrive() {
    }

    public int getIdTestDrive() {
        return idTestDrive;
    }

    public void setIdTestDrive(int idTestDrive) {
        this.idTestDrive = idTestDrive;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

}
