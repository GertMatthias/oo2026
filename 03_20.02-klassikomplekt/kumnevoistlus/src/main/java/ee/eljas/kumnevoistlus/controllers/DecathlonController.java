package ee.eljas.kumnevoistlus.controllers;

import ee.eljas.kumnevoistlus.entity.Athlete;
import ee.eljas.kumnevoistlus.entity.DecathlonResult;
import ee.eljas.kumnevoistlus.repository.AthleteRepository;
import ee.eljas.kumnevoistlus.repository.DecathlonResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/decathlon")
public class DecathlonController {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private DecathlonResultRepository resultRepository;

    // SPORTLASE LISAMINE JA NÄITAMINE

    @GetMapping("/athletes")
    public List<Athlete> getAthletes() {
        return athleteRepository.findAll();
    }

    @PostMapping("/athletes")
    public Athlete addAthlete(@RequestBody Athlete athlete) {
        if (athlete.getName() == null || athlete.getName().isBlank()) {
            throw new IllegalArgumentException("Sportlase nimi on kohustuslik!");
        }
        return athleteRepository.save(athlete);
    }

    // TULEMUSE LISAMINE JA PUNKTIDE ARVUTAMINE

    @PostMapping("/results")
    public DecathlonResult addResult(@RequestBody DecathlonResult result) {
        if (result.getEvent() == null || result.getEvent().isBlank()) {
            throw new IllegalArgumentException("Spordiala (event) on kohustuslik!");
        }

        if (result.getPerformance() <= 0) {
            throw new IllegalArgumentException("Tulemus peab olema nullist suurem!");
        }

        if (result.getAthlete() == null || result.getAthlete().getId() == null) {
            throw new IllegalArgumentException("Tulemusel peab olema määratud sportlase ID!");
        }

        Optional<Athlete> dbAthlete = athleteRepository.findById(result.getAthlete().getId());
        if (dbAthlete.isEmpty()) {
            throw new IllegalArgumentException("Sellise ID-ga sportlast ei leitud!");
        }

        result.setAthlete(dbAthlete.get());

        int points = calculatePoints(result.getEvent(), result.getPerformance());
        result.setPoints(points);

        return resultRepository.save(result);
    }




    @GetMapping("/athletes/{id}/total-points")
    public String getTotalPoints(@PathVariable Long id) {
        List<DecathlonResult> athleteResults = resultRepository.findByAthleteId(id);

        int totalSum = 0;
        for (DecathlonResult result : athleteResults) {
            totalSum += result.getPoints();
        }

        return "Sportlase (ID: " + id + ") kogusumma on: " + totalSum + " punkti.";
    }


    // ABIMEETOD PUNKTIDE ARVUTAMISEKS


    private int calculatePoints(String event, double performance) {
        String eventLower = event.toLowerCase();

        if (eventLower.equals("100m")) {
            // 100m valem: 25.4347 * (18 - tulemus sekundites)^1.81
            if (performance >= 18) return 0; // Üle 18 sekundi punkte ei saa
            return (int) (25.4347 * Math.pow(18.0 - performance, 1.81));

        } else if (eventLower.equals("kaugushüpe")) {
            double cm = performance * 100;
            if (cm <= 220) return 0;
            return (int) (0.14354 * Math.pow(cm - 220.0, 1.4));

        } else {
            throw new IllegalArgumentException("Süsteem ei oska veel ala '" + event + "' punkte arvutada! Proovi '100m' või 'kaugushüpe'.");
        }
    }
}
