package org.notetaking.markdownnotetakingapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileApiResponse {
    private String message;
    private HttpStatus status;
    private int code;
    private LocalDateTime timestamp;
    private String path;
    private Object data;
}
