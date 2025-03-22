package org.notetaking.markdownnotetakingapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.notetaking.markdownnotetakingapi.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        ApiResponse exception = new ApiResponse();
        exception.setMessage("Unexpected error: " + ex.getMessage());
        exception.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exception.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exception.setTimestamp(LocalDateTime.now());
        exception.setPath(request.getRequestURI());

        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse> handleIOException(IOException ex, HttpServletRequest request) {
        ApiResponse exception = new ApiResponse();
        exception.setMessage("File upload failed");
        exception.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exception.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exception.setTimestamp(LocalDateTime.now());
        exception.setPath(request.getRequestURI());

        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundException(CustomNotFoundException ex, HttpServletRequest request) {
        ApiResponse exception = new ApiResponse();

        exception.setStatus(HttpStatus.NOT_FOUND);
        exception.setCode(HttpStatus.NOT_FOUND.value());
        exception.setMessage(ex.getMessage());
        exception.setTimestamp(LocalDateTime.now());
        exception.setPath(request.getRequestURI());

        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
