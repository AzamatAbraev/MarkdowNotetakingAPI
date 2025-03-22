package org.notetaking.markdownnotetakingapi.repository;

import org.notetaking.markdownnotetakingapi.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteEntity, Integer> {
}
