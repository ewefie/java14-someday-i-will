package pl.somday.java14.security.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.somday.java14.newsletter.SendGridEmailService;
import pl.somday.java14.user.AppUser;

import java.util.Locale;

@Service
public class SignupEmailService {
    private static final String WELCOME_EMAIL_TEMPLATE = "welcome-email.html";

    private final Logger logger = LoggerFactory.getLogger(SignupEmailService.class);
    private final SendGridEmailService sendGridEmailService;
    private final TemplateEngine textTemplateEngine;

    public SignupEmailService(final SendGridEmailService sendGridEmailService,
                              final TemplateEngine textTemplateEngine) {
        this.sendGridEmailService = sendGridEmailService;
        this.textTemplateEngine = textTemplateEngine;
    }

    public void sendRegistrationConfirmationEmail(final AppUser appUser) {
        final String htmlContent = createWelcomeEmailHtmlContent(appUser.getName());
        sendGridEmailService.sendHtmlMail(htmlContent, appUser.getEmail(), "Welcome to Someday I will!");
        logger.info("Registration confirmation mail sent");
    }

    private String createWelcomeEmailHtmlContent(final String name) {
        final Locale locale = new Locale("en");
        final Context ctx = new Context(locale);
        ctx.setVariable("name", name);
        return textTemplateEngine.process(WELCOME_EMAIL_TEMPLATE, ctx);
    }
}
