package com.trilogy.noteservice.controller;

import com.trilogy.noteservice.dao.NoteDao;
import com.trilogy.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class NoteController {

    @Autowired
    NoteDao noteDao;

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note) {
        return noteDao.addNote(note);
    }

    @RequestMapping (value = "/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Note getNote(@PathVariable int id) {
        if (id < 1) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }
        Note returnVal = noteDao.getNote(id);
        if (returnVal == null) {
            throw new IllegalArgumentException("No note with id " + id);
        }

        return returnVal;
    }

    @RequestMapping (value = "/notes/book/{book_id}", method = RequestMethod.GET)
    @ResponseStatus (value = HttpStatus.OK)
    public List<Note> getNotesByBook(@PathVariable int book_id) {
        return noteDao.getNoteByBook(book_id);
    }

    @RequestMapping (value = "notes", method = RequestMethod.GET)
    @ResponseStatus (value = HttpStatus.OK)
    public List<Note> getAllNotes() {
        return noteDao.getAllNotes();
    }

    @RequestMapping (value = "/notes/{id}", method = RequestMethod.PUT)
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void updateNote(@RequestBody Note note, @PathVariable int id) {
        if (id != note.getNoteId()) {
            throw new IllegalArgumentException("Book ID on path must match the ID in the Book object.");
        }
    }

    @RequestMapping (value = "/notes/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable int id) {
    }
}
