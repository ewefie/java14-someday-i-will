package pl.somday.java14.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.somday.java14.newsletter.NewsletterFrequency;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    @Override
    Optional<AppUser> findById(Long id);

    Boolean existsByEmail(String email);

    List<AppUser> findAllByNewsletterFrequency(NewsletterFrequency newsletterFrequency);
}
