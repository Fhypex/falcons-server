package gtu.cse.se.altefdirt.aymoose.shared.application;

public record Response<T> (int status, boolean success, String message, T response) {    
    public static <T> Response<T> success(T response) {
        return new Response<>(200, true, "Success", response);
    }

    public static <T> Response<T> success(T response, String message) {
        return new Response<>(200, true, message, response);
    }

    public static <T> Response<T> failure(String message) {
        return new Response<T>(400, false, message, null);
    }

    public static <T> Response<T> created(T response) {
        return new Response<T>(201, true, "Created", response);
    }

    public static <T> Response<T> notFound() {
        return new Response<>(404, false, "Not Found", null);
    }

    public static <T> Response<T> badRequest() {
        return new Response<>(400, false, "Bad Request", null);
    }

    public static <T> Response<T> unauthorized() {
        return new Response<>(401, false, "Unauthorized", null);
    }

    public static <T> Response<T> forbidden() {
        return new Response<>(403, false, "Forbidden", null);
    }

    public static <T> Response<T> internalServerError() {
        return new Response<>(500, false, "Internal Server Error", null);
    }

    public static <T> Response<T> conflict() {
        return new Response<>(409, false, "Conflict", null);
    }

    public static <T> Response<T> noContent() {
        return new Response<>(204, true, "No Content", null);
    }

    public static <T> Response<T> notImplemented() {
        return new Response<>(501, false, "Not Implemented", null);
    }
}
