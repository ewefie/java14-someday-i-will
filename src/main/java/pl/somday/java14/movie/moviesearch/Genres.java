package pl.somday.java14.movie.moviesearch;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Genres(@JsonProperty("genres") Genre[]genres) {
}
