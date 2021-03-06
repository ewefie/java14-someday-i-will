package pl.somday.java14.newsletter;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SendGridEmailService {
    private static final String EMAIL_FROM = "someday-i-will@someday-i-will.com";
    private static final String SENDER_NAME = "Ewelina from Someday I Will";
    private final SendGrid sendGrid;
    private final Logger logger = LoggerFactory.getLogger(SendGridEmailService.class);

    public SendGridEmailService(final SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public Response sendHtmlMail(final String htmlContent, final String emailTo, final String subject) {
        var from = new Email(EMAIL_FROM, SENDER_NAME);
        var content = new Content("text/html", htmlContent);
        var to = new Email(emailTo);
        var mail = new Mail(from, subject, to, content);
        var request = new Request();
        Response response = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = sendGrid.api(request);
            logger.info("Status code: " + response.getStatusCode());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        return response;
    }
}
