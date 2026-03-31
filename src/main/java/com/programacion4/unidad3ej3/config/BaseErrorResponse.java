package com.programacion4.unidad3ej3.config;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseErrorResponse {

    private final String timestamp;
    private final int status;
    private final String error;
    private final List<String> errors;
    private final String path;

    public static BaseErrorResponse of(int status, String error, List<String> errors, String path) {
        return BaseErrorResponse.builder()
                .timestamp(currentTimestamp())
                .status(status)
                .error(error)
                .errors(errors)
                .path(path)
                .build();
    }

    private static String currentTimestamp() {
        return DateTimeFormatter.ISO_INSTANT
                .withZone(ZoneOffset.UTC)
                .format(Instant.now());
    }
}