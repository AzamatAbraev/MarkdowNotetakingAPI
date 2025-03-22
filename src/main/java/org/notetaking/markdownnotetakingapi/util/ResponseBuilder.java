package org.notetaking.markdownnotetakingapi.util;

import jakarta.servlet.http.HttpServletRequest;
import org.notetaking.markdownnotetakingapi.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseBuilder {
    public static ResponseEntity<ApiResponse> build(String message, HttpStatus status, Object data, String path) {
        ApiResponse response = new ApiResponse();
        response.setMessage(message);
        response.setStatus(status);
        response.setCode(status.value());
        response.setTimestamp(LocalDateTime.now());
        response.setPath(path);
        response.setData(data);

        return new ResponseEntity<>(response, status);
    }
}
