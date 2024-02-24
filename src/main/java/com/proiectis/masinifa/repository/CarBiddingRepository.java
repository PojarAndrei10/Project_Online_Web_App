package com.proiectis.masinifa.repository;

import com.proiectis.masinifa.entitati.CarBidding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarBiddingRepository extends JpaRepository<CarBidding, Integer> {
    // Aceasta interfata extinde JpaRepository, care ofera metode predefinite pentru operatii CRUD
    // si interactiuni cu baza de date pentru entitatea CarBidding, utilizand un tip de ID de tip Integer.

    @Query(value = "SELECT MAX(bid_price) FROM tb_car_bid WHERE id_car = :id", nativeQuery = true)
    int highestBid(@Param("id") int id);
    // Aceasta metoda va returna cea mai mare licitatie pentru masina cu ID-ul specificat.
    // Parametrul @Param("id") este utilizat pentru a inlocui :id din interogarea SQL cu valoarea parametrului "id".
    // nativeQuery = true specifica faptul ca se utilizeaza o interogare SQL nativa, nu JPQL (Java Persistence Query Language).
}
