package gtu.cse.se.altefdirt.aymoose.shared.api.rest.response;

public record CommandResponse<T>(T data, int totalResults, String flashMessage, boolean success)  {
    
    public CommandResponse(T data, int totalResults, String flashMessage) {
        this(data, totalResults, flashMessage, true);
    }

    public CommandResponse(T data, int totalResults) {
        this(data, totalResults, null, true);
    }

    public CommandResponse(T data) {
        this(data, 1, null, true);
    }

    public CommandResponse() {
        this(null, 0, null, true);
    }

    public CommandResponse(String flashMessage) {
        this(null, 0, flashMessage, false);
    }

    public CommandResponse(String flashMessage, boolean success) {
        this(null, 0, flashMessage, success);
    }
}