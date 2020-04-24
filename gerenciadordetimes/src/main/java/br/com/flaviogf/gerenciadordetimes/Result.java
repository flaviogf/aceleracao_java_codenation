package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public class Result<T> {
    private final T value;
    private final String error;

    private Result(T value, String error) {
        this.value = value;
        this.error = error;
    }

    public boolean isSuccess() {
        return error.isEmpty();
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public Optional<T> getValue() {
        if (value == null) {
            return Optional.empty();
        }

        return Optional.of(value);
    }

    public String getError() {
        return error;
    }

    public static <T> Result<T> ok() {
        return new Result<>(null, "");
    }

    public static <T> Result<T> ok(T value) {
        return new Result<>(value, "");
    }

    public static <T> Result<T> fail(String error) {
        return new Result<>(null, error);
    }
}
