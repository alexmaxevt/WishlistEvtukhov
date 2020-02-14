package ru.evtukhov.android.wishlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FileNoteRepository implements NoteRepository {
    private Context context;
    private final static String SAVE_FILE_NOTE = "notes";
    private Note note;

    private static Gson gson = new Gson();

    static void exportToJSON(Context context, Note note) {
        SharedPreferences preferences = getPreferences(context);
        preferences.edit()
                .putString(note.getDeadlineDate(), gson.toJson(note))
                .apply();
    }

    static void remove(Context context, Note note) {
        SharedPreferences preferences = getPreferences(context);
        preferences.edit()
                .remove(note.getDeadlineDate())
                .apply();
    }

    static List<Note> importFromJSON(Context context) {
        SharedPreferences preferences = getPreferences(context);
        Map<String, ?> all = preferences.getAll();
        List<Note> result = new ArrayList<>(all.size());
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String noteSerialized = (String) entry.getValue();
            result.add(gson.fromJson(noteSerialized, Note.class));
        }
        Collections.sort(result, new Comparator<Note>() {
            @SuppressLint("Assert")
            @Override
            public int compare(Note thisNote, Note anotherNote) {
                if (anotherNote.getDeadlineDate() != null) {
                    return anotherNote.getDeadlineDate().compareTo(thisNote.getDeadlineDate()) + anotherNote.getDeadlineDate().compareTo(thisNote.getDeadlineDate());
                }
                assert false;
                return anotherNote.getDeadlineDate().compareTo(thisNote.getDeadlineDate());
            }
        });
        return result;
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(SAVE_FILE_NOTE, Context.MODE_PRIVATE);
    }


    FileNoteRepository(Context context) {
        this.context = context;
    }

    @Override
    public Note getNoteById(String id) {
        return note;
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
