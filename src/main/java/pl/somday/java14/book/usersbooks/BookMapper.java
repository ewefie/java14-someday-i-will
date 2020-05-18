package pl.somday.java14.book.usersbooks;

import pl.somday.java14.book.booksearch.GBook;

import java.util.Map;

import static java.util.Objects.isNull;

public class BookMapper {
    private BookMapper() {
    }

    private static final int DESCRIPTION_MAX_LENGTH = 997;

    static String imageLinksMapToString(final Map<String, String> imageLinks) {
        return imageLinks.get("smallThumbnail");
    }

    public static Book mapBookDtoToBook(BookDto bookDto) {
        if (bookDto == null) {
            return null;
        }

        var book = new Book();

        book.setDescription(trimToLongDescription(bookDto.description()));
        book.setId(bookDto.id());
        book.setCategories(bookDto.categories());
        book.setTitle(bookDto.title());
        book.setSubtitle(bookDto.subtitle());
        book.setAuthors(bookDto.authors());
        book.setPageCount(bookDto.pageCount());
        book.setBuyLink(bookDto.buyLink());
        book.setImageLink(bookDto.imageLink());

        return book;
    }

    public static BookDto mapGBookToBookDto(GBook gBook) {
        if (gBook == null) {
            return null;
        }

        return new BookDto(null, trimToLongDescription(gBook.description()), gBook.title(), null,
                stringArrayToString(gBook.authors()), gBook.pageCount(), gBook
                .canonicalVolumeLink(), imageLinksMapToString(gBook
                .imageLinks()), stringArrayToString(gBook
                .categories()));
    }

    static String stringArrayToString(final String[] array) {
        return isNull(array) ? "" : String.join(", ", array);
    }

    private static String trimToLongDescription(final String description) {
        if (isNull(description)) {
            return "";
        }
        return description.length() < DESCRIPTION_MAX_LENGTH ? description : description
                .substring(0, DESCRIPTION_MAX_LENGTH) + "...";
    }

    public static BookDto mapBookToBookDto(Book book) {
        if (book == null) {
            return null;
        }

        return new BookDto(book.getId(), book.getDescription(), book.getTitle(), book.getSubtitle(),
                book.getAuthors(), book.getPageCount(), book.getBuyLink(), book.getImageLink(), book.getCategories());
    }
}
