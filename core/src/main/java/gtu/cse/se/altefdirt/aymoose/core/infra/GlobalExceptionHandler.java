package gtu.cse.se.altefdirt.aymoose.core.infra;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gtu.cse.se.altefdirt.aymoose.shared.application.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception e) {
        // You can customize the error code based on the exception
        return Response.failure(e.getMessage());
    }
}