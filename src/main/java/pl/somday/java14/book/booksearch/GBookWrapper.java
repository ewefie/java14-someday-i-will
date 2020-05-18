package pl.somday.java14.book.booksearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GBookWrapper(@JsonProperty("volumeInfo") GBook volumeInfo) {
}
