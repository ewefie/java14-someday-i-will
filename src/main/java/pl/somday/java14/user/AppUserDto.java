package pl.somday.java14.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AppUserDto(@JsonProperty("name")String name, @JsonProperty("email")String email) {
}
