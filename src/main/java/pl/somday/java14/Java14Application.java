package pl.somday.java14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.somday.java14.config.AppProperties;

@EnableConfigurationProperties(AppProperties.class)
@EnableScheduling
@SpringBootApplication
public class Java14Application {

    public static void main(String[] args) {
        SpringApplication.run(Java14Application.class, args);
    }

}
