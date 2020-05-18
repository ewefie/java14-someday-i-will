package pl.somday.java14.security.user;

import pl.somday.java14.user.AppUser;

public class SignupRequestMapper {
    private SignupRequestMapper() {
    }

    public static AppUser map(final SignupRequest signUpRequest) {
        var user = new AppUser();
        user.setPassword(signUpRequest.getPassword());
        user.setEmail(signUpRequest.getEmail());
        user.setEmail(signUpRequest.getEmail());
        user.setNewsletterFrequency(signUpRequest.getNewsletterFrequency());
        return user;
    }
}
