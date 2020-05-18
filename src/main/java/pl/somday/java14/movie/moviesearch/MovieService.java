package pl.somday.java14.movie.moviesearch;


import pl.somday.java14.movie.usersmovies.Movies;

public interface MovieService {

    Movies searchMoviesByTitle(String query);
}
