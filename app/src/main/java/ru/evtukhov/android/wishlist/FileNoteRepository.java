package ru.evtukhov.android.wishlist;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

public class FileNoteRepository implements NoteRepository {
    private Context context;
    private static Gson gson = new Gson();
    private final static String SAVE_FILE_NOTE = "notes";
    private SharedPreferences sharedPreferences;

    public void readJson(Note note) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor = (SharedPreferences.Editor) context.getSharedPreferences(SAVE_FILE_NOTE, Context.MODE_PRIVATE);
        editor.putString(note.getDeadlineDate(), gson.toJson(note));
        editor.apply();
    }

    public void delete (Context context, Note note) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor = (SharedPreferences.Editor) context.getSharedPreferences(SAVE_FILE_NOTE, Context.MODE_PRIVATE);
        editor.remove(note.getDeadlineDate());
        editor.apply();
    }

    public FileNoteRepository(Context context) {
        this.context = context;
    }

    @Override
    public Note getNoteById(String id) {
        return null;
    }

    @Override
    public List<Note> getNotes() {
        return null;
    }

    @Override
    public void saveNote(Note note) {

    }

    @Override
    public void deleteById(String id) {

    }
}
