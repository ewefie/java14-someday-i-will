package pl.somday.java14.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties(prefix = "app")
@Validated
public class AppProperties {
    private final Auth auth = new Auth();
    private final Cron cron = new Cron();
    private final GoogleBooks googleBooks = new GoogleBooks();
    private final MovieDatabase movieDatabase = new MovieDatabase();

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static class Auth {
        @NotBlank
        private String tokenSecret;
        @NotNull
        private long tokenExpirationMills;
    }

    private static class Cron {
        @NotBlank
        private String weeklyPattern;
        @NotBlank
        private String monthlyPattern;
    }

    private static class GoogleBooks {
        @NotBlank
        private String key;
        @NotBlank
        private String baseUrl;
    }

    private static class MovieDatabase {
        @NotBlank
        private String key;
        @NotBlank
        private String baseUrl;
    }
}
