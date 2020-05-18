package pl.somday.java14.book.booksearch;


import pl.somday.java14.book.usersbooks.Books;

public interface BookService {
    Books searchBooksByTitle(String title);

    Books searchBooksByAuthor(String author);
}
