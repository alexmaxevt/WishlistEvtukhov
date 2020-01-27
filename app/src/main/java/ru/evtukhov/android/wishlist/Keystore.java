package ru.evtukhov.android.wishlist;

public interface Keystore {
    boolean hasPin();
    boolean checkPin(String pin);
    void saveNew(String pin);
}
