package br.com.flaviogf.gerenciadordetimes;

public class Result {
    private final String error;

    private Result(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return error.isEmpty();
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public String getError() {
        return error;
    }

    public static Result ok() {
        return new Result("");
    }

    public static Result fail(String error) {
        return new Result(error);
    }
}
