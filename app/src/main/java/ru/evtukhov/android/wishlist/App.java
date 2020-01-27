package ru.evtukhov.android.wishlist;

import android.app.Application;

public class App extends Application {
    private static NoteRepository noteRepository;
    private static Keystore keystore;
    private final static String APP_PREFERENCES = "keysettings";

    @Override
    public void onCreate() {
        super.onCreate();
        noteRepository = new FileNoteRepository(this);
        keystore = new SimpleKeystore(getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE));
    }
    public NoteRepository getNoteRepository() {
        return noteRepository;
    }
    public Keystore getKeystore() {
        return keystore;
    }
}
