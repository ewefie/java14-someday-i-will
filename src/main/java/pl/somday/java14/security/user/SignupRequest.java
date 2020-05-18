package pl.somday.java14.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import pl.somday.java14.newsletter.NewsletterFrequency;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static java.util.Objects.nonNull;

public class SignupRequest {
    @NotNull(message = "Name has to be provided")
    @Length(min = 2, message = "Name has to be at at least 2 character long")
    private String name;

    @NotNull(message = "Email address has to be provided")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password has to be provided")
    private String password;

    @Enumerated(EnumType.STRING)
    private NewsletterFrequency newsletterFrequency;

    @JsonIgnore
    @AssertTrue(message = "Password has to be at least 8 characters and contain at least one digit, one lower case and one upper case letter")
    public boolean isPasswordValid() {
        return nonNull(password) && password.length() >= 8 &&
                password.chars().anyMatch(Character::isDigit) &&
                password.chars().anyMatch(Character::isLowerCase) &&
                password.chars().anyMatch(Character::isUpperCase);
    }

    public SignupRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public NewsletterFrequency getNewsletterFrequency() {
        return newsletterFrequency;
    }
}
