package org.notetaking.markdownnotetakingapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.notetaking.markdownnotetakingapi.model.FileApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FileApiResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        FileApiResponse exception = new FileApiResponse();
        exception.setMessage("Unexpected error: " + ex.getMessage());
        exception.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exception.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exception.setTimestamp(LocalDateTime.now());
        exception.setPath(request.getRequestURI());

        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(IOException.class)
    public ResponseEntity<FileApiResponse> handleIOException(IOException ex, HttpServletRequest request) {
        FileApiResponse exception = new FileApiResponse();
        exception.setMessage("File upload failed");
        exception.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exception.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exception.setTimestamp(LocalDateTime.now());
        exception.setPath(request.getRequestURI());

        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<FileApiResponse> handleNotFoundException(CustomNotFoundException ex) {
        FileApiResponse exception = new FileApiResponse();

        exception.setStatus(HttpStatus.NOT_FOUND);
        exception.setCode(HttpStatus.NOT_FOUND.value());
        exception.setMessage(ex.getMessage());
        exception.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
