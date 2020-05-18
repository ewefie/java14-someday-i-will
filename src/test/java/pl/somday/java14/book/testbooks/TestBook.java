package pl.somday.java14.book.testbooks;

import com.github.javafaker.Faker;
import pl.somday.java14.book.usersbooks.Book;

public class TestBook {
    public static Book aRandomBook() {
        var faker = new Faker();
        return Book.builder()
                .title(faker.book().title())
                .subtitle(faker.book().title())
                .authors(faker.book().author())
                .categories(faker.book().genre())
                .description(faker.lorem().sentence())
                .pageCount(faker.number().numberBetween(10, 500))
                .imageLink(faker.internet().url())
                .buyLink(faker.internet().url())
                .build();
    }
}
