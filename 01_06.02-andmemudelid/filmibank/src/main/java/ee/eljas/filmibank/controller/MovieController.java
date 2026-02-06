package ee.eljas.filmibank.controller;

import ee.eljas.filmibank.entity.Movie;
import ee.eljas.filmibank.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("movies")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @DeleteMapping("movies/{id}")
    public List<Movie> deleteMovies(@PathVariable Long id) {
        movieRepository.deleteById(id);
        return movieRepository.findAll();
    }

    @PostMapping("movies")
    public List<Movie> addMovies(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return movieRepository.findAll();
    }

}
