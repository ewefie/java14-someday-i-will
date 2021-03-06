package pl.somday.java14.user;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.somday.java14.exceptions.ResourceNotFoundException;
import pl.somday.java14.newsletter.NewsletterFrequency;
import pl.somday.java14.security.user.SignupEmailService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final SignupEmailService mailService;

    public AppUserService(final AppUserRepository appUserRepository, final SignupEmailService mailService) {
        this.appUserRepository = appUserRepository;
        this.mailService = mailService;
    }

    public AppUser saveUser(final AppUser user) {
        mailService.sendRegistrationConfirmationEmail(user);
        return appUserRepository.save(user);
    }

    public AppUserDto getUserDtoByAppUserId(final Long userId) {
        var user = getExistingUser(userId);
        return AppUserMapper.map(user);
    }

    public void deleteUser(final Long id) {
        appUserRepository.deleteById(id);
    }

    public AppUser getExistingUser(final Long id) {
        return getOptionalUser(id).orElseThrow(() ->
                new ResourceNotFoundException("User with given id does not exist"));
    }

    private Optional<AppUser> getOptionalUser(final Long id) {
        return appUserRepository.findById(id);
    }

    public List<AppUser> getAllUsersForMonthlyNewsletter() {
        return appUserRepository.findAllByNewsletterFrequency(NewsletterFrequency.MONTHLY);
    }

    public List<AppUser> getAllUsersForWeeklyNewsletter() {
        return appUserRepository.findAllByNewsletterFrequency(NewsletterFrequency.WEEKLY);
    }

    public boolean existsByEmail(final String email) {
        return appUserRepository.existsByEmail(email);
    }
}
