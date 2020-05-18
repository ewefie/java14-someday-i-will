package pl.somday.java14.book.usersbooks;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookDto(@JsonProperty("id")Long id,
                      @JsonProperty("description")String description,
                      @JsonProperty("title")String title,
                      @JsonProperty("subtitle")String subtitle,
                      @JsonProperty("authors")String authors,
                      @JsonProperty("pageCount")Integer pageCount,
                      @JsonProperty("buyLink")String buyLink,
                      @JsonProperty("imageLink")String imageLink,
                      @JsonProperty("categories")String categories) {
}
