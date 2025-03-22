package org.notetaking.markdownnotetakingapi.service;

import org.notetaking.markdownnotetakingapi.exception.CustomNotFoundException;
import org.notetaking.markdownnotetakingapi.model.FileEntity;
import org.notetaking.markdownnotetakingapi.model.NoteEntity;
import org.notetaking.markdownnotetakingapi.repository.FileRepository;
import org.notetaking.markdownnotetakingapi.repository.NoteRepository;
import org.notetaking.markdownnotetakingapi.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final Converter converter;

    @Autowired
    public NoteService(NoteRepository noteRepository, Converter converter) {
        this.noteRepository = noteRepository;
        this.converter = converter;
    }

    public List<NoteEntity> getNotes() {
        return noteRepository.findAll();
    }

    public Optional<NoteEntity> getNoteById(Integer id) {
        return noteRepository.findById(id);
    }

    @Transactional
    public NoteEntity saveNote(MultipartFile file) throws IOException {

        if (file.getContentType() == null || !file.getContentType().equals("text/markdown")) {
            throw new IllegalArgumentException("File is not a valid Markdown (.md) file");
        }

        String markdownContent = new String(file.getBytes());

        NoteEntity note = new NoteEntity();
        note.setTitle(file.getOriginalFilename());
        note.setMarkdownContent(markdownContent);
        note.setHtmlContent(converter.toHtml(markdownContent));

        return noteRepository.save(note);
    }


}
