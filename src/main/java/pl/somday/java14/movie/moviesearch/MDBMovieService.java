package pl.somday.java14.movie.moviesearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.somday.java14.movie.usersmovies.MovieDto;
import pl.somday.java14.movie.usersmovies.MovieMapper;
import pl.somday.java14.movie.usersmovies.Movies;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class MDBMovieService implements MovieService {
    private final RestTemplate restTemplate;
    @Value("${app.movie-database.base-url}")
    private String movieApiBaseUrl;
    @Value("${app.movie-database.key}")
    private String movieApiKey;

    public MDBMovieService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Movies searchMoviesByTitle(final String query) {
        var fullPath = getFullPath(query);
        var apiResponse = restTemplate.getForEntity(fullPath, MDBWrapper.class);
        final List<MovieDto> movieDtos = nonNull(apiResponse.getBody()) ? mapResponseBodyToMovieDtoList(apiResponse
                .getBody()) : Collections.emptyList();
        return new Movies(movieDtos);
    }

    private Map<Integer, String> getGenresMap() {
        var apiResponse = restTemplate.getForEntity(getGenresPath(), Genres.class);
        if (nonNull(apiResponse.getBody())) {
            var genresWrapper = apiResponse.getBody();
            if (nonNull(genresWrapper.genres())) {
                return Arrays.stream(genresWrapper.genres())
                        .collect(Collectors.toMap(Genre::id, Genre::name));
            }
        }
        return Collections.emptyMap();
    }

    private List<MovieDto> mapResponseBodyToMovieDtoList(final MDBWrapper wrapper) {
        var genresMap = getGenresMap();
        if (nonNull(wrapper.results())) {
            return Arrays.stream(wrapper.results())
                    .map(mdbMovie -> MovieMapper.mapMDBMovieToMovieDto(mdbMovie, genresMap))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private String getFullPath(final String query) {
        return movieApiBaseUrl +
                "/search/movie?api_key=" +
                movieApiKey +
                "&query=" +
                query.replace(" ", "%20");
    }

    private String getGenresPath() {
        return movieApiBaseUrl +
                "/genre/movie/list?api_key=" +
                movieApiKey;
    }
}
