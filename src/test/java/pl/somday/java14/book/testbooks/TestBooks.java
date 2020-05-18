package pl.somday.java14.book.testbooks;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.somday.java14.book.usersbooks.BookDto;
import pl.somday.java14.book.usersbooks.Books;

import java.io.IOException;
import java.util.ArrayList;

public class TestBooks {

    private static final Logger logger = LoggerFactory.getLogger(TestBooks.class);

    public static Books withListOfRandomBooks(final int listSize) {
        var bookDtoList = new ArrayList<BookDto>();
        for (int i = 0; i < listSize; i++) {
            bookDtoList.add(TestBookDto.aRandomBookDto());
        }
        return new Books(bookDtoList);
    }

    public static Books fromJSONString(final String booksAsString) {
        try {
            return new ObjectMapper().readValue(booksAsString, Books.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
