package org.notetaking.markdownnotetakingapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.notetaking.markdownnotetakingapi.exception.CustomNotFoundException;
import org.notetaking.markdownnotetakingapi.model.FileEntity;
import org.notetaking.markdownnotetakingapi.model.ApiResponse;
import org.notetaking.markdownnotetakingapi.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadFile(
            @RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        FileEntity savedFile = fileService.saveFile(file);

        ApiResponse response = new ApiResponse();
        response.setMessage("File uploaded successfully");
        response.setStatus(HttpStatus.CREATED);
        response.setCode(HttpStatus.CREATED.value());
        response.setTimestamp(LocalDateTime.now());
        response.setPath(request.getRequestURI());
        response.setData(savedFile);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id) {
        Optional<FileEntity> file = fileService.downloadFile(id);

        if (file.isEmpty()) {
            throw new CustomNotFoundException("File with id " + id + " is not found");
        }

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.get().getFileName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, file.get().getFileType())
                .body(file.get().getFileData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFile(@PathVariable Integer id, HttpServletRequest request) {
        if (!fileService.deleteFile(id)) {
            throw new CustomNotFoundException("File with id " + id + " is not found");
        }

        return ResponseEntity.noContent().build();
    }
}
