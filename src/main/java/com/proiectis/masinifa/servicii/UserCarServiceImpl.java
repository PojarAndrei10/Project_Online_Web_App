package com.proiectis.masinifa.servicii;

import com.proiectis.masinifa.entitati.*;
import com.proiectis.masinifa.repository.CarBiddingRepository;
import com.proiectis.masinifa.repository.CarPictureRepository;
import com.proiectis.masinifa.repository.CarRepository;
import com.proiectis.masinifa.repository.TestDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
public class UserCarServiceImpl implements UserCarService {

    @Autowired
    private UserService userService; // Gestionarea utilizatorilor

    @Autowired
    private CarRepository carRepo; // Repository pentru operatiile legate de masini

    @Autowired
    private CarPictureRepository carPictureRepo; // Repository pentru operatiile legate de pozele masinilor

    @Autowired
    private CarBiddingRepository carBidRepo; // Repository pentru operatiile legate de licitatii pentru masini

    @Autowired
    private TestDriveRepository testDriveRepo; // Repository pentru operatiile legate de test drive-uri

    @Override
    public List<Car> listUserCar() {
        // Obtine lista tuturor masinilor
        List<Car> listUserCar = carRepo.findAll();

        // Obtine utilizatorul autentificat
        UserAccount user = userService.getUserLogin();

        // Elimina masinile care nu apartin utilizatorului autentificat
        listUserCar.removeIf(car -> car.getUser() != user);

        return listUserCar;
    }

    @Override
    public void postCar(MultipartFile file, Car car) throws Exception {
        // Obtine utilizatorul autentificat
        UserAccount user = userService.getUserLogin();

        // Creeaza o instanta de CarPicture si CarBidding
        CarPicture picture = new CarPicture();
        CarBidding carBidding = new CarBidding();

        // Seteaza detaliile imaginii masinii
        picture.setFileName(file.getOriginalFilename());
        picture.setFileType(file.getContentType());
        picture.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        picture.setCar(car); // Asociaza imaginea cu masina

        // Seteaza detaliile masinii si asocierea cu utilizatorul
        car.setCarPicture(picture);
        car.setStatus("DEZACTIVAT");
        car.setUser(user);

        // Seteaza detaliile licitatiei asociate masinii
        carBidding.setCar(car);
        carBidding.setUser(user);
        carBidding.setBidPrice(0);
        carBidding.setStatus("PRIMU_PRET");

        carRepo.save(car);
        carBidRepo.save(carBidding);
    }

    @Override
    public void editCar(Car car) {
        Car editedCar = carRepo.findById(car.getIdCar()).get();

        // Actualizeaza detaliile masinii cu noile informatii
        editedCar.setMake(car.getMake());
        editedCar.setModel(car.getModel());
        editedCar.setYear(car.getYear());
        editedCar.setPrice(car.getPrice());

        carRepo.save(editedCar);
    }

    @Override
    public void activateCarPost(int id) {
        Car editedCar = carRepo.findById(id).get();

        // Actualizează statusul mașinii la "ACTIV"
        editedCar.setStatus("ACTIV");

        carRepo.save(editedCar);
    }

    @Override
    public void deactivateCarPost(int id) {
        Car editedCar = carRepo.findById(id).get();

        // Actualizează statusul mașinii la "DEZACTIVAT"
        editedCar.setStatus("DEZACTIVAT");

        carRepo.save(editedCar);
    }

    @Override
    public List<CarBidding> listCarBid() {
        // Obține o listă de toate licitațiile de mașini din baza de date
        List<CarBidding> listCarBid = carBidRepo.findAll();

        return listCarBid;
    }

    @Override
    public Car getCarById(int id) {
        // Obține mașina din baza de date bazată pe ID-ul specificat
        Car car = carRepo.findById(id).get();

        return car;
    }

    @Override
    public void postCarBidding(CarBidding carBidding) {
        // Setează statusul licitației ca "IN_DESFASURARE"
        carBidding.setStatus("IN_DESFASURARE");

        carBidRepo.save(carBidding);
    }

    @Override
    public int highestBidding(int idCar) {
        // Obține cea mai mare ofertă pentru o anumită mașină din baza de date
        int carBidding = carBidRepo.highestBid(idCar);

        return carBidding;
    }

    @Override
    public void saveTestDrive(String date, UserAccount user, Car car) {
        // Creează un obiect TestDrive și setează detaliile acestuia
        TestDrive testDrive = new TestDrive();

        testDrive.setDate(date);
        testDrive.setCar(car);
        testDrive.setUser(user);

        testDriveRepo.save(testDrive);
    }

    @Override
    public List<TestDrive> listTestDrive() {
        // Obține o listă de toate test drive-urile din baza de date
        List<TestDrive> listTestDrive = testDriveRepo.findAll();

        return listTestDrive;
    }

    @Override
    public void saveUploadPicture(MultipartFile file, Car car) throws Exception {
        try {
            // Obține imaginea mașinii
            CarPicture picture = car.getCarPicture();

            // Actualizează detaliile imaginii mașinii cu informațiile furnizate
            picture.setFileName(file.getOriginalFilename());
            picture.setFileType(file.getContentType());
            picture.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            picture.setCar(car);

            carPictureRepo.save(picture);
        } catch (Exception e) {
            // Gestionează și afișează mesajul de eroare în caz de excepție
            System.out.println(e.getMessage());
        }
    }

}