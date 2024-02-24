package com.proiectis.masinifa.servicii;

import com.proiectis.masinifa.entitati.Car;
import com.proiectis.masinifa.entitati.CarBidding;
import com.proiectis.masinifa.entitati.UserAccount;
import com.proiectis.masinifa.entitati.UserProfile;

import java.util.List;

public interface AdminService {

    void editUser(UserProfile profile);

    void markAsAdmin(int idUser);

    UserProfile getProfileById(int idCar);

    List<UserAccount> listUser();

    List<UserAccount> listAdmin();

    List<Car> listCar();

    List<CarBidding> listCarBid();

    void approveCarBid(int idBid);

    void denyCarBid(int idBid);
}