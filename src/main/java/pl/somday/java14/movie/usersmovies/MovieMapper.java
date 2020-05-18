package pl.somday.java14.movie.usersmovies;

import pl.somday.java14.movie.moviesearch.MDBMovie;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class MovieMapper {
    private static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w154";
    private static final int DESCRIPTION_MAX_LENGTH = 997;

    private MovieMapper() {
    }

    public static MovieDto mapMovieToMovieDto(Movie movie) {
        if (movie == null) {
            return null;
        }

        return new MovieDto(movie.getId(), movie.getGenres(), movie.getTitle(),
                movie.getReleaseDate(),
                movie.getPosterLink(), movie.getDescription());
    }

    public static Movie mapMovieDtoToMovie(MovieDto movieDto) {
        if (movieDto == null) {
            return null;
        }

        Movie movie = new Movie();

        movie.setDescription(MovieMapper.trimToLongDescription(movieDto.description()));
        movie.setId(movieDto.id());
        movie.setGenres(movieDto.genres());
        movie.setTitle(movieDto.title());
//        movie.setReleaseDate(movieDto.releaseDate());
        movie.setPosterLink(movieDto.posterLink());

        return movie;
    }

    public static MovieDto mapMDBMovieToMovieDto(final MDBMovie mdbMovie, final Map<Integer, String> genresMap) {
        if (mdbMovie == null) {
            return null;
        }

        var genres = Arrays.stream(mdbMovie.genre_ids())
                .map(genresMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));

        return new MovieDto(null, genres, mdbMovie.title(),
                mdbMovie.release_date(),
                getFullPosterLink(mdbMovie
                        .poster_path()), trimToLongDescription(mdbMovie.overview()));
    }

    private static String getFullPosterLink(final String posterPath) {
        return isNull(posterPath) ? "" : POSTER_BASE_URL + posterPath;
    }

    private static String trimToLongDescription(final String description) {
        if (isNull(description)) {
            return "";
        }
        return description.length() < DESCRIPTION_MAX_LENGTH ? description : description
                .substring(0, DESCRIPTION_MAX_LENGTH) + "...";
    }
}
