package com.proiectis.masinifa.repository;

import com.proiectis.masinifa.entitati.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    // Metoda searchCar() efectueaza o cautare a masinilor in baza de date in functie de cuvantul cheie (keyword).
    // Aceasta utilizeaza o interogare nativa SQL pentru a cauta in campurile make, model si year ale entitatii Car.
    @Query(value = "SELECT * FROM tb_car "
            + "WHERE make LIKE '%' :keyword '%' "
            + "OR model LIKE '%' :keyword '%' "
            + "OR year LIKE '%' :keyword '%' ", nativeQuery = true)
    List<Car> searchCar(@Param("keyword") String keyword);

    // Metoda searchCarByPriceRange() cauta masini in functie de un interval de pret specificat.
    @Query(value = "SELECT * FROM tb_car "
            + "WHERE price >= :low AND price <= :high", nativeQuery = true)
    List<Car> searchCarByPriceRange(@Param("low") int low, @Param("high") int high);

    // Metoda searchCarByKeywordAndPriceRange() combina cautarea dupa cuvant cheie si intervalul de pret.
    // Aceasta cauta masini care corespund cuvantului cheie si se incadreaza in intervalul specificat de pret.
    @Query(value = "SELECT * FROM tb_car "
            + "WHERE make LIKE '%' :keyword '%' "
            + "OR model LIKE '%' :keyword '%' "
            + "OR year LIKE '%' :keyword '%' "
            + "AND price >= :low AND price <= :high", nativeQuery = true)
    List<Car> searchCarByKeywordAndPriceRange(@Param("keyword") String keyword, @Param("low") int low,
                                              @Param("high") int high);

    // Metoda featuredCars() returneaza o lista de masini considerate ca fiind "highlight" (caracteristice),
    // in functie de criterii specifice (status = 'ACTIV') si se sorteaza aleatoriu, selectand doar 3 masini.
    @Query(value = "SELECT * FROM tb_car WHERE status = 'ACTIV' ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<Car> featuredCars();
}
