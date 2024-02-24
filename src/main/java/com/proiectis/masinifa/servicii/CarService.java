package com.proiectis.masinifa.servicii;

import com.proiectis.masinifa.entitati.Car;

import java.util.List;

public interface CarService {

    Car getCarById(int id);

    List<Car> listCar();

    List<Car> searchCar(String keyword);

    List<Car> searchCarByPriceRange(int low, int high);

    List<Car> searchCarByKeywordAndPriceRange(String keyword, int low, int high);

    List<Car> featuredCars();
}