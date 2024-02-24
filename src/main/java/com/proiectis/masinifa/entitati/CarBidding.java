package com.proiectis.masinifa.entitati;

import javax.persistence.*;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "tb_car_bid")
public class CarBidding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bid")
    private int idBid; //

    @Digits(integer = 10, fraction = 2)
    @Column(nullable = false)
    private int bidPrice; // Pretul oferit pentru licitatie

    private String status; // Starea licitatiei (poate fi "activa", "finalizata", etc.)

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserAccount user; // Utilizatorul care a facut oferta pentru licitatie

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car")
    private Car car; // Masina pentru care s-a facut oferta de licitatie

    public CarBidding() {
    }

    public int getIdBid() {
        return idBid;
    }

    public void setIdBid(int idBid) {
        this.idBid = idBid;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
