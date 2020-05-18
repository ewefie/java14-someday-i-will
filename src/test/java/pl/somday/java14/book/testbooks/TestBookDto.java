package pl.somday.java14.book.testbooks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import pl.somday.java14.book.usersbooks.BookDto;

import java.io.IOException;

public class TestBookDto {
    private static final Logger logger = LoggerFactory.getLogger(TestBookDto.class);

    private TestBookDto() {
    }

    public static BookDto aRandomBookDto() {
        var faker = new Faker();
        return new BookDto(null, faker.overwatch().quote(), faker.book().title(), null, faker.book()
                .author(), faker.number().randomDigitNotZero(), faker.internet().url(),
                faker.internet().url(), faker.book().genre());
    }

    public static String asJSONString(final BookDto bookDto) {
        var bookAsJSONString = new JSONObject();
        try {
            bookAsJSONString.put("title", bookDto.title());
            bookAsJSONString.put("subtitle", bookDto.subtitle());
            bookAsJSONString.put("authors", bookDto.authors());
            bookAsJSONString.put("categories", bookDto.categories());
            bookAsJSONString.put("description", bookDto.description());
            bookAsJSONString.put("pageCount", bookDto.pageCount());
            bookAsJSONString.put("imageLink", bookDto.imageLink());
            bookAsJSONString.put("buyLink", bookDto.buyLink());
            bookAsJSONString.put("id", bookDto.id());
        } catch (JSONException e) {
            logger.error(e.getMessage());
        }
        return bookAsJSONString.toString();
    }

    public static BookDto fromJSONString(final String bookAsJSONString) {
        try {
            return new ObjectMapper().readValue(bookAsJSONString, BookDto.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
