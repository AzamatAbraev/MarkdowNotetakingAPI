package org.notetaking.markdownnotetakingapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.notetaking.markdownnotetakingapi.exception.CustomNotFoundException;
import org.notetaking.markdownnotetakingapi.model.ApiResponse;
import org.notetaking.markdownnotetakingapi.model.NoteEntity;
import org.notetaking.markdownnotetakingapi.service.NoteService;
import org.notetaking.markdownnotetakingapi.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notes")
@CrossOrigin
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllNotes(HttpServletRequest request) {
        List<NoteEntity> notes = noteService.getNotes();

        return ResponseBuilder.build("Success", HttpStatus.OK, notes, request.getRequestURI());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getNoteById(@PathVariable Integer id, HttpServletRequest request) {
        Optional<NoteEntity> note = noteService.getNoteById(id);

        if (note.isEmpty()) {
            throw new CustomNotFoundException("Note is not found");
        }

        return ResponseBuilder.build("Success", HttpStatus.OK, note.get(), request.getRequestURI());
    }

    @GetMapping("/html/{id}")
    public ResponseEntity<String> getNoteAsHtml(@PathVariable Integer id) {
        Optional<NoteEntity> note = noteService.getNoteById(id);

        if (note.isEmpty()) {
            throw new CustomNotFoundException("Note is not found");
        }

        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(note.get().getHtmlContent());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveNote(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        NoteEntity note = noteService.saveNote(file);

        return ResponseBuilder.build("Success", HttpStatus.CREATED, note, request.getRequestURI());
    }
}
