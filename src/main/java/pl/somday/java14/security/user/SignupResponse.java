package pl.somday.java14.security.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SignupResponse(@JsonProperty("message")String message) {
}
