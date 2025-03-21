package org.notetaking.markdownnotetakingapi.service;

import org.notetaking.markdownnotetakingapi.exception.CustomNotFoundException;
import org.notetaking.markdownnotetakingapi.model.FileData;
import org.notetaking.markdownnotetakingapi.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileData saveFile(MultipartFile file) throws IOException {
        FileData fileData = new FileData();
        fileData.setFileName(file.getOriginalFilename());
        fileData.setFileType(file.getContentType());
        fileData.setFileData(file.getBytes());

        return fileRepository.save(fileData);
    }

    public Optional<FileData> downloadFile(Integer id) {
        return fileRepository.findById(id);
    }

    public boolean deleteFile(Integer id) {

        if (!fileRepository.existsById(id)) {
            return false;
        }

        fileRepository.deleteById(id);
        return true;
    }
}
