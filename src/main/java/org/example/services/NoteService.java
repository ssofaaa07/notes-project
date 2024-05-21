package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entities.Image;
import org.example.entities.Note;
import org.example.repositories.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> listNotes() {
        return noteRepository.findAllByOrderByIdDesc();
    }

    public void saveNote(Note note, MultipartFile file) throws IOException {
        if (!note.getNoteText().equals("") || file.getSize() != 0) {
            if (file.getSize() != 0) {
                Image image = fileToImageEntity(file);
                note.addImage(image);
            }
            noteRepository.save(note);
        }
    }

    private Image fileToImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setBytes(file.getBytes());
        image.setFileName(file.getOriginalFilename());
        return image;
    }

    public void updateNote(Note note, Long id, MultipartFile file) throws IOException {
        Note updateNote = noteRepository.findById(id).orElse(null);
        if (updateNote != null) {
            updateNote.setNoteText(note.getNoteText());
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            note.setNoteDate(sqlDate);
            this.saveNote(updateNote, file);
        }
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public long count() {
        return noteRepository.count();
    }

}
