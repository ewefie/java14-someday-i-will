package pl.somday.java14.movie.usersmovies;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.somday.java14.security.user.CurrentUser;
import pl.somday.java14.security.user.UserPrincipal;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/me/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public MovieDto getMovieById(@CurrentUser final UserPrincipal userPrincipal,
                                 @PathVariable(name = "movieId") final Long movieId) {
        return movieService.getUsersMovie(movieId, userPrincipal.getId());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public Movies getAllMovies(@CurrentUser final UserPrincipal userPrincipal) {
        return movieService.getAllUsersMovies(userPrincipal.getId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto addMovie(@Valid @RequestBody final MovieDto movieDto,
                             @CurrentUser final UserPrincipal userPrincipal) {
        return movieService.saveMovie(movieDto, userPrincipal.getId());
    }

    @DeleteMapping(value = "/{movieId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovieById(@CurrentUser final UserPrincipal userPrincipal,
                                @PathVariable(name = "movieId") final Long movieId) {
        movieService.deleteUsersMovie(movieId, userPrincipal.getId());
    }

    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllMovies(@CurrentUser final UserPrincipal userPrincipal) {
        movieService.deleteAllUsersMovies(userPrincipal.getId());
    }
}
