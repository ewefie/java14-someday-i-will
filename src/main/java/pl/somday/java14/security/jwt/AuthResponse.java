package pl.somday.java14.security.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(@JsonProperty("accessToken")String accessToken,
                           @JsonProperty("tokenType")String tokenType) {
    public AuthResponse(final String accessToken) {
        this(accessToken, "Bearer");
    }
}
