package com.trilogy.noteservice.dao;

import com.trilogy.noteservice.model.Note;

import java.util.List;

public interface NoteDao {

    Note addNote (Note note);

    Note getNote (int id);

    List<Note> getNoteByBook(int bookId);

    List<Note> getAllNotes();

    void updateNote (Note note);

    void deleteNote (int id);

}
