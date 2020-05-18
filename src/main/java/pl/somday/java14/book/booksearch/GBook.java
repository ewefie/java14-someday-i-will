package pl.somday.java14.book.booksearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GBook(@JsonProperty("title")String title,
                    @JsonProperty("publisher")String publisher,
                    @JsonProperty("publishedDate")String publishedDate,
                    @JsonProperty("authors")String[]authors,
                    @JsonProperty("description")String description,
                    @JsonProperty("pageCount")Integer pageCount,
                    @JsonProperty("categories")String[]categories,
                    @JsonProperty("canonicalVolumeLink")String canonicalVolumeLink,
                    @JsonProperty("language")String language,
                    @JsonProperty("previewLink")String previewLink,
                    @JsonProperty("imageLinks")Map<String, String>imageLinks) {
}
