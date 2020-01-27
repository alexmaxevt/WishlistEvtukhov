package ru.evtukhov.android.wishlist;

import android.content.Context;
import android.content.SharedPreferences;

public class SimpleKeystore implements Keystore {

    private final static String APP_PREFERENCES = "keysettings";
    private final static String APP_PREFERENCES_PIN = "pin";
    private SharedPreferences sharedPreferences;
    private Context context;

    public SimpleKeystore(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public boolean hasPin() {
        return sharedPreferences.getString(APP_PREFERENCES_PIN, "").isEmpty();
    }

    @Override
    public boolean checkPin(String pin) {
        return sharedPreferences.getString(APP_PREFERENCES_PIN, "").equals(pin);
    }

    @Override
    public void saveNew(String pin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(APP_PREFERENCES_PIN)) {
            removeOld();
        }
        else {
            editor.putString(APP_PREFERENCES_PIN, pin);
            editor.apply();
        }
    }

    private void removeOld() {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(SimpleKeystore.APP_PREFERENCES_PIN);
        edit.apply();
    }
}
