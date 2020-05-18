package pl.somday.java14.book.usersbooks;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pl.somday.java14.book.testbooks.TestBook;
import pl.somday.java14.book.testbooks.TestBookDto;
import pl.somday.java14.book.testbooks.TestGBook;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BookMapperTest {
    private static final Faker FAKER = new Faker();

    @Test
    void shouldMapBookToBookDto() {
        var book = TestBook.aRandomBook();

        var bookDto = BookMapper.mapBookToBookDto(book);

        assertAll(
                () -> assertThat(bookDto).isNotNull(),
                () -> assertThat(bookDto.authors()).isEqualTo(book.getAuthors()),
                () -> assertThat(bookDto.buyLink()).isEqualTo(book.getBuyLink()),
                () -> assertThat(bookDto.imageLink()).isEqualTo(book.getImageLink()),
                () -> assertThat(bookDto.title()).isEqualTo(book.getTitle()),
                () -> assertThat(bookDto.description()).isEqualTo(book.getDescription()),
                () -> assertThat(bookDto.pageCount()).isEqualTo(book.getPageCount()),
                () -> assertThat(bookDto.categories()).isEqualTo(book.getCategories())
        );
    }

    @Test
    void shouldMapBookDtoToBook() {
        var bookDto = TestBookDto.aRandomBookDto();

        var book = BookMapper.mapBookDtoToBook(bookDto);
        assertAll(
                () -> assertThat(book).isNotNull(),
                () -> assertThat(book.getAuthors()).isEqualTo(bookDto.authors()),
                () -> assertThat(book.getBuyLink()).isEqualTo(bookDto.buyLink()),
                () -> assertThat(book.getImageLink()).isEqualTo(bookDto.imageLink()),
                () -> assertThat(book.getTitle()).isEqualTo(bookDto.title()),
                () -> assertThat(book.getDescription()).isEqualTo(bookDto.description()
                        .length() < 7997 ? bookDto.description() : bookDto.description()
                        .substring(0, 7997) + "..."),
                () -> assertThat(book.getPageCount()).isEqualTo(bookDto.pageCount()),
                () -> assertThat(book.getCategories()).isEqualTo(bookDto.categories())
        );
    }

    @Test
    void shouldMapGBookToBookDto() {
        var gBook = TestGBook.aRandomGBook();

        var bookDto = BookMapper.mapGBookToBookDto(gBook);
        assertAll(
                () -> assertThat(bookDto).isNotNull(),
                () -> assertThat(bookDto.authors()).isEqualTo(gBook.authors()[0] + ", " + gBook.authors()[1]),
                () -> assertThat(bookDto.buyLink()).isEqualTo(gBook.canonicalVolumeLink()),
                () -> assertThat(bookDto.imageLink()).isEqualTo(gBook.imageLinks().get("smallThumbnail")),
                () -> assertThat(bookDto.title()).isEqualTo(gBook.title()),
                () -> assertThat(bookDto.description()).isEqualTo(gBook.description()),
                () -> assertThat(bookDto.pageCount()).isEqualTo(gBook.pageCount()),
                () -> assertThat(bookDto.categories())
                        .isEqualTo(gBook.categories()[0] + ", " + gBook.categories()[1])
        );
    }

    @Test
    void shouldConvertStringArrayToString() {
        var authorsAsAnArray = new String[]{FAKER.book().author(), FAKER.book().author()};

        var authorsAsString = BookMapper.stringArrayToString(authorsAsAnArray);

        assertThat(authorsAsString).isEqualTo(authorsAsAnArray[0] + ", " + authorsAsAnArray[1]);
    }

    @Test
    void shouldConvertImageLinksMapToString() {
        var imageLinks = Collections.singletonMap("smallThumbnail", FAKER.internet().url());

        var imageLink = BookMapper.imageLinksMapToString(imageLinks);

        assertThat(imageLink).isEqualTo(imageLinks.get("smallThumbnail"));
    }
}
