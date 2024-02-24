package com.proiectis.masinifa.servicii;

import com.proiectis.masinifa.entitati.Car;
import com.proiectis.masinifa.entitati.CarBidding;
import com.proiectis.masinifa.entitati.TestDrive;
import com.proiectis.masinifa.entitati.UserAccount;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserCarService {

    List<Car> listUserCar();

    void postCar(MultipartFile file, Car car) throws Exception;

    void editCar(Car car);

    void activateCarPost(int id);

    void deactivateCarPost(int id);

    List<CarBidding> listCarBid();

    Car getCarById(int id);

    void postCarBidding(CarBidding carBidding);

    int highestBidding(int idCar);

    void saveTestDrive(String date, UserAccount user, Car car);

    List<TestDrive> listTestDrive();

    void saveUploadPicture(MultipartFile file, Car car) throws Exception;
}