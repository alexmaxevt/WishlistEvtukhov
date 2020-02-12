package ru.evtukhov.android.wishlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteAdapter extends BaseAdapter {

    private Context context;
    private List<Note> noteList = Collections.emptyList();
    private LayoutInflater layoutInflater;
    private NoteRepository noteRepository = App.getNoteRepository();
    private AlertDialog.Builder dialog;

    public NoteAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.note_item, parent, false);
        }
        final Note note = getNote(position);
        TextView textViewTitle = view.findViewById(R.id.headNote);
        TextView textViewBody = view.findViewById(R.id.bodyNote);
        TextView textViewDeadline = view.findViewById(R.id.dateNote);
        final String title = note.getTitle();
        final String body = note.getBody();
        String deadline = note.getDeadlineDate();
        boolean isChecked = note.isHasDeadline();
        if ("".equals(title)) {
            textViewTitle.setVisibility(View.GONE);
        } else {
            textViewBody.setText(title);
        }
        if ("".equals(body)) {
            textViewBody.setVisibility(View.GONE);
        } else {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY", Locale.getDefault());
            String date = dateFormat.format(new Date());
            textViewBody.setText(body);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note editNote = new Note(title, body,
                        note.isHasDeadline(), note.getDeadlineDate());
                Intent intent = new Intent(context, CreateNoteActivity.class);
                intent.putExtra(Note.class.getSimpleName(), (Parcelable) editNote);
                context.startActivity(intent);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.app_deleteDialogTitle)
                        .setMessage(R.string.app_deleteDialogMessage)
                        .setIcon(R.drawable.ic_trash)
                        .setCancelable(true)
                        .setPositiveButton(R.string.app_deleteDialogPositiveButton, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removeNote(position);
                            }
                        })
                        .setNegativeButton(R.string.app_deleteDialogNegativeButton, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                return false;
            }
        });
        return view;
    }

    private Note getNote(int position) {
        return noteList.get(position);
    }

    private void removeNote(int position) {
        Note note = noteList.get(position);
        noteList.remove(position);
        try {
            FileNoteRepository.remove(context, note);
        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    private void addNote(Note itemNote) {
        this.noteList.add(itemNote);
        notifyDataSetChanged();
    }
}
