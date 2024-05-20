package org.example.repositories;

import org.example.entities.Notes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends CrudRepository<Notes, Long> {
    List<Notes> findAllByOrderByNoteIdDesc();

}
