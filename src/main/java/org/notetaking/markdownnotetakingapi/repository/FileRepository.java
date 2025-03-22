package org.notetaking.markdownnotetakingapi.repository;

import org.notetaking.markdownnotetakingapi.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Integer> {
}
