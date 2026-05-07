package ee.eljas.kumnevoistlus.entity;

import jakarta.persistence.*;

@Entity
public class DecathlonResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String event;       // Ala
    private double performance; // Tulemus
    private int points;

    @ManyToOne
    private Athlete athlete;

    public DecathlonResult() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }

    public double getPerformance() { return performance; }
    public void setPerformance(double performance) { this.performance = performance; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public Athlete getAthlete() { return athlete; }
    public void setAthlete(Athlete athlete) { this.athlete = athlete; }
}
