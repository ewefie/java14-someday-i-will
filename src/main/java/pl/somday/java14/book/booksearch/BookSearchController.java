package pl.somday.java14.book.booksearch;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.somday.java14.book.usersbooks.Books;

@RestController
@RequestMapping("/books/search")
public class BookSearchController {

    private final BookService bookService;

    public BookSearchController(final GoogleBooksService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "title")
    @ResponseStatus(HttpStatus.OK)
    public Books getBooksByTitle(
            @RequestParam(name = "title") final String title) {
        return bookService.searchBooksByTitle(title);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "author")
    @ResponseStatus(HttpStatus.OK)
    public Books getBooksByAuthor(
            @RequestParam(name = "author") final String author) {
        return bookService.searchBooksByAuthor(author);
    }
}
