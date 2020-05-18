package pl.somday.java14.movie.moviesearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.somday.java14.movie.usersmovies.Movies;

@RestController
@RequestMapping("/movies/search")
public class MovieSearchController {

    private final MovieService movieService;

    public MovieSearchController(final MDBMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "title")
    @ResponseStatus(HttpStatus.OK)
    public Movies getMoviesByTitle(
            @RequestParam(name = "title") final String query) {
        return movieService.searchMoviesByTitle(query);
    }
}
