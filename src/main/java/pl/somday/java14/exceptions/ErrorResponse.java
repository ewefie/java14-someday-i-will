package pl.somday.java14.exceptions;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        @JsonProperty("timestamp") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")LocalDateTime timeStamp,
        @JsonProperty("errors")List<String>errors
) {
}
