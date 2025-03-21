package org.notetaking.markdownnotetakingapi.repository;

import org.notetaking.markdownnotetakingapi.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileData, Integer> {
}
