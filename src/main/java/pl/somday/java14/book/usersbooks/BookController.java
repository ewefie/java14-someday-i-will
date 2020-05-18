package pl.somday.java14.book.usersbooks;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.somday.java14.security.user.CurrentUser;
import pl.somday.java14.security.user.UserPrincipal;

@RestController
@RequestMapping("/users/me/books")
public class BookController {

    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookById(
            @CurrentUser final UserPrincipal userPrincipal,
            @PathVariable(name = "bookId") final Long bookId) {
        return bookService.getUsersBook(bookId, userPrincipal.getId());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public Books getAllBooks(@CurrentUser final UserPrincipal userPrincipal) {
        return bookService.getAllUsersBooks(userPrincipal.getId());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto addBook(@RequestBody final BookDto bookDto,
                           @CurrentUser final UserPrincipal userPrincipal) {
        return bookService.saveBook(bookDto, userPrincipal.getId());
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@CurrentUser final UserPrincipal userPrincipal,
                               @PathVariable(name = "bookId") final Long bookId) {
        bookService.deleteUsersBook(bookId, userPrincipal.getId());
    }

    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllBooks(@CurrentUser final UserPrincipal userPrincipal) {
        bookService.deleteAllUsersBooks(userPrincipal.getId());
    }
}
