package org.example.controllers;

import org.example.entities.Notes;
import org.example.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private NotesRepository notesRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Notes> notes = notesRepository.findAllByOrderByNoteIdDesc();
        model.addAttribute("note", new Notes());
        model.addAttribute("notes", notes);
        model.addAttribute("title", "Заметки");
        return "home";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("note") Notes note) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        note.setNoteDate(sqlDate);
        notesRepository.save(note);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<Notes> note = notesRepository.findById(id);
        model.addAttribute("note", note.get());
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("note") Notes note, @PathVariable("id") Long id) {
        Notes updateNote = notesRepository.findById(id).orElseThrow();
        updateNote.setNoteText(note.noteText);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        updateNote.setNoteDate(sqlDate);
        notesRepository.save(updateNote);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        notesRepository.deleteById(id);
        return "redirect:/";
    }


}
