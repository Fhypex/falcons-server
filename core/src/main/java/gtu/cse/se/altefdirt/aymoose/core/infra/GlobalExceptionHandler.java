package gtu.cse.se.altefdirt.aymoose.core.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gtu.cse.se.altefdirt.aymoose.shared.application.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleException(Exception e) {
        // You can customize the error code based on the exception
        Response<String> response = Response.failure(e.getMessage());
        return ResponseEntity.status(response.status()).body(response);
    }
}