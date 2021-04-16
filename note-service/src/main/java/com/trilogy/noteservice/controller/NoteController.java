package com.trilogy.noteservice.controller;

import com.trilogy.noteservice.dao.NoteDao;
import com.trilogy.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    @Autowired
    NoteDao noteDao;

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note) {
        return noteDao.addNote(note);
    }

    @RequestMapping (value = "/notes/{note_id}", method = RequestMethod.GET)
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

    // TODO: getNotesByBook

    @RequestMapping (value = "notes", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getAllNotes() {
        return noteDao.getAllNotes();
    }

    @RequestMapping (value = "/notes/{note_id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNote(@RequestBody Note note, @PathVariable int note_id) {
        if (note_id != note.getNoteId()) {
            throw new IllegalArgumentException("Book ID on path must match the ID in the Book object.");
        }
    }

    @RequestMapping (value = "/notes/{note_id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable int note_id) {

    }
}
