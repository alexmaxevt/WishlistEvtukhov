package ru.evtukhov.android.wishlist;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Класс FileKeystore
 * данный класс сохраняает новый пароль в файле,
 * а также проверяет его наличие, считывает его и проверяет на соответствие в файле
 * <p>
 * Класс использует интерфейс Keystore
 */
public class FileKeystore implements Keystore {

    private final static String FILE_KEYSTORE = "keysettings";
    private Context context;

    FileKeystore(Context context) {
        this.context = context;
    }

    @Override
    public boolean hasPin() {
        return !TextUtils.isEmpty(readFilePass());
    }

    @Override
    public boolean checkPin(String pin) {
        return pin.equals(readFilePass());
    }

    @Override
    public void saveNew(String pin) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(FILE_KEYSTORE, Context.MODE_PRIVATE)))) {
            bufferedWriter.write(pin);
            bufferedWriter.close();
            Toast.makeText(context, R.string.app_toastPassSave, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, R.string.app_toastPassDontSave, Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    String readFilePass() {
        try (FileInputStream fileInputStream = context.openFileInput(FILE_KEYSTORE);
             InputStreamReader inputStream = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStream)) {
            return bufferedReader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
