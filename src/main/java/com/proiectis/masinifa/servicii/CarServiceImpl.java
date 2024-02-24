package com.proiectis.masinifa.servicii;

import com.proiectis.masinifa.entitati.Car;
import com.proiectis.masinifa.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepo; // Repository pentru operatiile legate de masini

    @Override
    public Car getCarById(int id) {
        // Obtine o masina din baza de date pe baza ID-ului specificat
        Car car = carRepo.findById(id).get();

        return car;
    }

    @Override
    public List<Car> listCar() {
        // Obtine o lista de toate masinile
        List<Car> listCar = carRepo.findAll();

        // Elimina masinile cu statusul "DEZACTIVAT" din lista returnata
        listCar.removeIf(car -> car.getStatus().equals("DEZACTIVAT"));

        return listCar;
    }

    @Override
    public List<Car> searchCar(String keyword) {
        // Cauta masini pe baza unui cuvant cheie
        List<Car> cars = carRepo.searchCar(keyword);

        // Elimina masinile cu statusul "DEZACTIVAT" din lista returnata
        cars.removeIf(car -> car.getStatus().equals("DEZACTIVAT"));

        return cars;
    }

    @Override
    public List<Car> searchCarByPriceRange(int low, int high) {
        // Cauta masini pe baza unei plaje de preturi
        List<Car> cars = carRepo.searchCarByPriceRange(low, high);

        // Elimina masinile cu statusul "DEZACTIVAT" din lista returnata
        cars.removeIf(car -> car.getStatus().equals("DEZACTIVAT"));

        return cars;
    }

    @Override
    public List<Car> searchCarByKeywordAndPriceRange(String keyword, int low, int high) {
        List<Car> cars = carRepo.searchCarByKeywordAndPriceRange(keyword, low, high);

        // Elimina masinile cu statusul "DEZACTIVAT" din lista returnata
        cars.removeIf(car -> car.getStatus().equals("DEZACTIVAT"));

        return cars;
    }

    @Override
    public List<Car> featuredCars() {
        // Obtine masinile recomandate/destinate in mod special
        List<Car> featuredCars = carRepo.featuredCars();

        return featuredCars;
    }

}
