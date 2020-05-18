package pl.somday.java14.newsletter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.somday.java14.book.usersbooks.BookDto;
import pl.somday.java14.book.usersbooks.BookService;
import pl.somday.java14.movie.usersmovies.MovieDto;
import pl.somday.java14.movie.usersmovies.MovieService;
import pl.somday.java14.user.AppUser;
import pl.somday.java14.user.AppUserService;

import java.util.List;
import java.util.Locale;

@Component
public class NewsletterService {
    private static final String NEWSLETTER_EMAIL_TEMPLATE = "newsletter-email.html";

    private final Logger logger = LoggerFactory.getLogger(NewsletterService.class);
    private final SendGridEmailService sendGridEmailService;
    private final AppUserService appUserService;
    private final BookService bookService;
    private final MovieService movieService;
    private final TemplateEngine textTemplateEngine;

    public NewsletterService(final SendGridEmailService sendGridEmailService, final AppUserService appUserService,
                             final BookService bookService, final MovieService movieService,
                             final TemplateEngine textTemplateEngine) {
        this.sendGridEmailService = sendGridEmailService;
        this.appUserService = appUserService;
        this.bookService = bookService;
        this.movieService = movieService;
        this.textTemplateEngine = textTemplateEngine;
    }

    @Scheduled(cron = "${app.cron.weekly-pattern}")
    void sendWeeklyNewsletter() {
        logger.info("Sending weekly newsletter");
        appUserService.getAllUsersForWeeklyNewsletter()
                .forEach(this::sendNewsletter);
    }

    @Scheduled(cron = "${app.cron.monthly-pattern}")
    void sendMonthlyNewsletter() {
        logger.info("Sending monthly newsletter");
        appUserService.getAllUsersForMonthlyNewsletter()
                .forEach(this::sendNewsletter);
    }

    void sendNewsletter(final AppUser appUser) {
        var frequency = appUser.getNewsletterFrequency().getValue();
        var subject = "Your " + frequency + " newsletter";
        var movies = movieService.getAllUsersMovies(appUser.getId()).movies();
        var books = bookService.getAllUsersBooks(appUser.getId()).bookDtos();
        var content = createNewsletterHtmlContent(appUser.getName(), movies, books, frequency);
        var email = appUser.getEmail();
        sendGridEmailService.sendHtmlMail(content, email, subject);
    }

    String createNewsletterHtmlContent(final String name, final List<MovieDto> movies, final List<BookDto> books,
                                       final String frequency) {
        var locale = new Locale("en");
        var context = new Context(locale);
        context.setVariable("frequency", frequency);
        context.setVariable("name", name);
        context.setVariable("books", books);
        context.setVariable("movies", movies);
        return textTemplateEngine.process(NEWSLETTER_EMAIL_TEMPLATE, context);
    }
}
