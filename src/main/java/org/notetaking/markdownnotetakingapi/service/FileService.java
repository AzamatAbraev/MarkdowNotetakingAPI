package org.notetaking.markdownnotetakingapi.service;

import org.notetaking.markdownnotetakingapi.model.FileEntity;
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

    public FileEntity saveFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFileData(file.getBytes());

        return fileRepository.save(fileEntity);
    }

    public Optional<FileEntity> downloadFile(Integer id) {
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
