package pl.somday.java14.user;


import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    @Test
    void shouldMapUserToUserDtoDto() {
        var faker = new Faker();
        var user = new AppUser();
        user.setEmail(faker.internet().emailAddress());
        user.setName(faker.name().firstName());
        user.setPassword(faker.internet().password());
        user.setCreatedAt(LocalDate.now());
        user.setId(faker.number().randomNumber());

        var appUserDto = AppUserMapper.map(user);

        assertThat(appUserDto).isNotNull();
        assertThat(appUserDto.email()).isEqualTo(user.getEmail());
        assertThat(appUserDto.name()).isEqualTo(user.getName());
    }
}
