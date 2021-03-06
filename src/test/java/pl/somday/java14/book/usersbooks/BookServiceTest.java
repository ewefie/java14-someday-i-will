package pl.somday.java14.book.usersbooks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import pl.somday.java14.book.booksearch.GoogleBooksService;
import pl.somday.java14.book.testbooks.TestBook;
import pl.somday.java14.book.testbooks.TestBookDto;
import pl.somday.java14.exceptions.ResourceNotFoundException;
import pl.somday.java14.user.AppUserService;
import pl.somday.java14.user.TestUsers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    AppUserService userService;

    @InjectMocks
    BookService bookService;

    @Test
    void shouldDeleteAllUsersBooksWhenUserIdGiven() {
        var userId = 5L;

        bookService.deleteAllUsersBooks(userId);

        verify(bookRepository, times(1)).deleteAllByUserId(userId);
    }

    @Test
    void shouldDeleteUsersBookWhenUserIdAndBookIdGiven() {
        var appUser = TestUsers.aUserWithRandomCredentials();
        appUser.setId(5L);
        var book = TestBook.aRandomBook();
        book.setId(13L);
        book.setUser(appUser);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        bookService.deleteUsersBook(book.getId(), appUser.getId());

        verify(bookRepository, times(1)).deleteById(book.getId());
    }

    @Test
    void shouldThrowAccessDeniedExceptionWhenGivenUserIdDoesNotMatchBookOwnerId() {
        var book = TestBook.aRandomBook();
        book.setId(12L);
        var appUser = TestUsers.aUserWithRandomCredentials();
        appUser.setId(5L);
        book.setUser(appUser);
        var userId = 23L;

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        assertThrows(AccessDeniedException.class, () -> bookService.getUsersBook(book.getId(), userId));
    }

    @Test
    void shouldReturnUsersBooksWhenUserWithGivenIdExist() {
        var booksToReturn = new Books(List.of(TestBookDto.aRandomBookDto()));
        var booksToReturnByRepository = booksToReturn.bookDtos()
                .stream()
                .map(BookMapper::mapBookDtoToBook)
                .collect(Collectors.toList());
        var userId = 3L;

        when(bookRepository.findAllByUserId(userId)).thenReturn(booksToReturnByRepository);
        var result = bookService.getAllUsersBooks(userId);

        assertEquals(booksToReturn, result);
    }

    @Test
    void shouldSaveBookWhenUserWithGivenIdExists() {
        var user = TestUsers.aUserWithRandomCredentials();
        user.setId(3L);
        var bookDtoToSave = TestBookDto.aRandomBookDto();

        when(userService.getExistingUser(user.getId())).thenReturn(user);
        bookService.saveBook(bookDtoToSave, user.getId());

        verify(bookRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenBookWithGivenIdDoesNotExist() {
        var bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService
                .getExistingBookById(bookId), "Book with given id does not exist");
    }
}
