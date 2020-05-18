package pl.somday.java14.movie.usersmovies;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record MovieDto(@JsonProperty("id")Long id,
                       @JsonProperty("genres")String genres,
                       @JsonProperty("title") @NotNull String title,
                       @JsonProperty("releaseDate") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")LocalDate releaseDate,
                       @JsonProperty("posterLink")String posterLink,
                       @JsonProperty("description")String description) {
}
