package org.example.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long noteId;
    public Date noteDate;
    public String noteText;

    public Notes() {
    }
    public Notes(Long noteId, Date noteDate, String noteText) {
        this.noteId = noteId;
        this.noteDate = noteDate;
        this.noteText = noteText;
    }

    public Notes(String noteText) {
        this.noteText = noteText;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

}
