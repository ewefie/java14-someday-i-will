package pl.somday.java14.user;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;
import pl.somday.java14.avatar.Avatar;
import pl.somday.java14.book.usersbooks.Book;
import pl.somday.java14.movie.usersmovies.Movie;
import pl.somday.java14.newsletter.NewsletterFrequency;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "email", updatable = false)
    private String email;

    @NonNull
    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Avatar avatar;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Book> books;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Movie> movies;

    @Enumerated(EnumType.STRING)
    @Column(name = "newsletter_frequency")
    private NewsletterFrequency newsletterFrequency;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull final String name) {
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull final String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull final String password) {
        this.password = password;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(final Avatar avatar) {
        this.avatar = avatar;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final List<Movie> movies) {
        this.movies = movies;
    }

    public NewsletterFrequency getNewsletterFrequency() {
        return newsletterFrequency;
    }

    public void setNewsletterFrequency(final NewsletterFrequency newsletterFrequency) {
        this.newsletterFrequency = newsletterFrequency;
    }
}
