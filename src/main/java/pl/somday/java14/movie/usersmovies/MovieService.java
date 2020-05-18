package pl.somday.java14.movie.usersmovies;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.somday.java14.exceptions.ResourceNotFoundException;
import pl.somday.java14.user.AppUserService;

import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;
    private final AppUserService userService;

    public MovieService(final MovieRepository movieRepository, final AppUserService userService) {
        this.movieRepository = movieRepository;
        this.userService = userService;
    }

    public MovieDto saveMovie(final MovieDto movieDto, final Long userId) {
        var user = userService.getExistingUser(userId);
        var movieToAdd = MovieMapper.mapMovieDtoToMovie(movieDto);
        movieToAdd.setUser(user);
        movieRepository.save(movieToAdd);
        return MovieMapper.mapMovieToMovieDto(movieToAdd);
    }

    public Movies getAllUsersMovies(final Long userId) {
        var movieDtoList = movieRepository.findAllByUserId(userId)
                .stream()
                .map(MovieMapper::mapMovieToMovieDto)
                .collect(Collectors.toList());
        return new Movies(movieDtoList);
    }

    public MovieDto getUsersMovie(final Long movieId, final Long userId) {
        var existingMovie = getExistingMovieById(movieId);
        if (existingMovie.getUser().getId().equals(userId)) {
            return MovieMapper.mapMovieToMovieDto(existingMovie);
        }
        throw new AccessDeniedException("You do not have permission to access this content");
    }

    public void deleteUsersMovie(final Long movieId, final Long userId) {
        var existingMovie = getExistingMovieById(movieId);
        if (!existingMovie.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You do not have permission to access this content");
        }
        movieRepository.deleteById(movieId);
    }

    public void deleteAllUsersMovies(final Long userId) {
        movieRepository.deleteAllByUserId(userId);
    }

    Movie getExistingMovieById(final Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with given id does not exist"));
    }
}
