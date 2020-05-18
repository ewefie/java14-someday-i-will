package pl.somday.java14.movie.moviesearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MDBMovie(@JsonProperty("title")String title,
                       @JsonProperty("overview")String overview,
                       @JsonProperty("release_date") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")LocalDate release_date,
                       @JsonProperty("poster_path")String poster_path,
                       @JsonProperty("genre_ids")Integer[]genre_ids) {
}
