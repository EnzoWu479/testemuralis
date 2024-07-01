package com.enzo.testemuralis.dto;

public record Response<T>(
    Boolean success,
    String message,
    T data
) {
    private Response(String message) {
        this(false, message, null);
    }
    public static <T> Response<T> error(String message) {
        return new Response<>(message);
    }
}
