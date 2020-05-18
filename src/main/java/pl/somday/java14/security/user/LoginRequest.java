package pl.somday.java14.security.user;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record LoginRequest(@JsonProperty("email") @NotNull @Email String email,
                           @JsonProperty("password") @NotNull String password) {
}
