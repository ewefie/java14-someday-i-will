package pl.somday.java14.book.testbooks;


import pl.somday.java14.book.booksearch.GBookWrapper;
import pl.somday.java14.book.booksearch.GBooks;
import pl.somday.java14.book.usersbooks.BookMapper;
import pl.somday.java14.book.usersbooks.Books;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestGBooks {
    public static Books toBooks(final GBooks gBooks) {
        return new Books(Arrays.stream(gBooks.items())
                .map(GBookWrapper::volumeInfo)
                .map(BookMapper::mapGBookToBookDto)
                .collect(Collectors.toList()));
    }
}
