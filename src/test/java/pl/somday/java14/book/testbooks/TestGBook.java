package pl.somday.java14.book.testbooks;

import com.github.javafaker.Faker;
import pl.somday.java14.book.booksearch.GBook;

import java.util.Collections;

public class TestGBook {


    public static GBook aRandomGBook() {
        var faker = new Faker();

        return new GBook(faker.book().title(),
                null,
                null,
                new String[]{faker.book().author(),
                faker.book().author()},
                faker.harryPotter().quote(),
                faker.number().randomDigitNotZero(),
                new String[]{faker.book().genre(),
                faker.book().genre()},
                faker.internet().url(),
                null,
                null,
                Collections.singletonMap("smallThumbnail", faker.internet().url()));
    }
}
