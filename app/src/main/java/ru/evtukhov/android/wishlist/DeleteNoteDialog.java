package ru.evtukhov.android.wishlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * class DeleteNoteDialog
 * В данном классе происходит создание диалогового окна удаения заметки,
 * а также методы для удаления заметки
 */
public class DeleteNoteDialog {

    private AlertDialog.Builder dialog;
    private AlertDialog alert;
    private Context context;

    // Окно подтверждения удаления заметки
    public void deleteDialog () {
        dialog = new AlertDialog.Builder(context);
        dialog.setIcon(R.drawable.ic_trash);
        dialog.setTitle(R.string.app_deleteDialogTitle);
        dialog.setMessage(R.string.app_deleteDialogMessage);
        dialog.setCancelable(true);
        dialog.setPositiveButton(R.string.app_deleteDialogPositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItem();
            }
        });
        dialog.setNegativeButton(R.string.app_deleteDialogNegativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    // Удание заметок из списка
    private void deleteItem () {

    }
}
