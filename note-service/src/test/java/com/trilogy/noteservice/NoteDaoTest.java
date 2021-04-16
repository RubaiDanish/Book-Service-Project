package com.trilogy.noteservice;

import com.trilogy.noteservice.dao.NoteDao;
import com.trilogy.noteservice.model.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoTest {
    @Autowired
    protected NoteDao dao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Note> nList = dao.getAllNotes();

        nList.stream()
                .forEach(note -> dao.deleteNote(note.getNoteId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteNote() {

        Note note = new Note();
        note.setBookId(123);
        note.setNote("Something");

        note = dao.addNote(note);

        Note note2 = dao.getNote(note.getNoteId());

        assertEquals(note, note2);

        dao.deleteNote(note.getNoteId());

        note2 = dao.getNote(note.getNoteId());

        assertNull(note2);
    }

    @Test
    public void getAllNotes() {

        Note note = new Note();
        note.setBookId(124);
        note.setNote("Something else");

        dao.addNote(note);

        note = new Note();
        note.setBookId(125);
        note.setNote("Something else etc");

        dao.addNote(note);

        List<Note> noteList = dao.getAllNotes();

        assertEquals(noteList.size(), 2);
    }

    @Test
    public void getNoteByBook() {
        Note note = new Note();
        note.setBookId(123);
        note.setNote("Something");

        dao.addNote(note);

        note = new Note();
        note.setBookId(124);
        note.setNote("Something else");

        dao.addNote(note);

        note = new Note();
        note.setBookId(125);
        note.setNote("Something else etc");

        dao.addNote(note);

        List<Note> nList = dao.getNoteByBook(123);
        assertEquals(2, nList.size());

        nList = dao.getNoteByBook(124);
        assertEquals(1, nList.size());

        nList = dao.getNoteByBook(125);
        assertEquals(0, nList.size());

    }

    @Test
    public void updateNote() {

        Note note = new Note();
        note.setBookId(123);
        note.setNote("Something");

        note = dao.addNote(note);

        note.setBookId(126);
        note.setNote("UPDATED");

        dao.updateNote(note);

        Note note2 = dao.getNote(note.getNoteId());

        assertEquals(note2, note);
    }
}
