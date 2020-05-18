package pl.somday.java14.movie.usersmovies;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Movies(@JsonProperty("movies")List<MovieDto>movies) {
}
