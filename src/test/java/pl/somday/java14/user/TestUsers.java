package pl.somday.java14.user;

import com.github.javafaker.Faker;

import static java.util.Objects.nonNull;

public class TestUsers {
    public static AppUser aUserWithRandomCredentials() {
        var faker = new Faker();
        var user = new AppUser();
        user.setEmail(faker.number().numberBetween(0, 100) + faker.internet()
                .emailAddress());
        user.setName(faker.name().username());
        user.setPassword(generateStrongPassword());
        return user;
    }

    public static AppUser aDefaultUser() {
        var user = new AppUser();
        user.setEmail("unique@email.com");
        user.setName("Sarah");
        user.setPassword("StrongPassword123");
        return user;
    }

    public static String generateStrongPassword() {
        var password = new Faker().internet().password(8, 16, true, false, true);
        if (isPasswordValid(password)) {
            return password;
        }
        return generateStrongPassword();
    }

    public static boolean isPasswordValid(String password) {
        return nonNull(password) && password.length() >= 8 &&
                password.chars().anyMatch(Character::isDigit) &&
                password.chars().anyMatch(Character::isLowerCase) &&
                password.chars().anyMatch(Character::isUpperCase);
    }
}
