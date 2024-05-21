package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.entities.Note;
import org.example.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("notes", noteService.listNotes());
        model.addAttribute("title", "Мои заметки");
        model.addAttribute("notesCount", noteService.count());
        return "home";
    }

    @PostMapping("/create")
    public String create(@RequestParam("file") MultipartFile file, @ModelAttribute("note") Note note) throws IOException {
        noteService.saveNote(note, file);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("title", "Редактирование заметки");
        model.addAttribute("images", noteService.getNoteById(id).getImages());
        model.addAttribute("note", noteService.getNoteById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@RequestParam("file") MultipartFile file, @ModelAttribute("note") Note note, @PathVariable("id") Long id) throws IOException {
        noteService.updateNote(note, id, file);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
        return "redirect:/";
    }


}
