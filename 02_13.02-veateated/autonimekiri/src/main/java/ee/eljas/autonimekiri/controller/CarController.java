package ee.eljas.autonimekiri.controller;

import ee.eljas.autonimekiri.entity.Car;
import ee.eljas.autonimekiri.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @PostMapping
    public List<Car> addCar(@RequestBody Car car) {

        if (car.getMake() == null || car.getMake().isBlank()) {
            throw new IllegalArgumentException("Auto mark (make) on kohustuslik!");
        }

        if (car.getModel() == null || car.getModel().length() < 2) {
            throw new IllegalArgumentException("Auto mudeli nimi peab olema vähemalt 2 tähte pikk!");
        }

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (car.getYear() < 1886 || car.getYear() > currentYear + 1) {
            throw new IllegalArgumentException("Auto väljalaskeaasta peab olema vahemikus 1886 kuni " + (currentYear + 1) + "!");
        }

        if (car.getPrice() < 0) {
            throw new IllegalArgumentException("Auto hind ei saa olla negatiivne!");
        }

        if (car.getLicensePlate() == null || car.getLicensePlate().isBlank()) {
            throw new IllegalArgumentException("Ilma numbrimärgita autot ei saa süsteemi lisada!");
        }

        carRepository.save(car);
        return carRepository.findAll();
    }
}
