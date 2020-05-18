package pl.somday.java14.book.usersbooks;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Books(@JsonProperty("bookDtos") List<BookDto>bookDtos) {
}
