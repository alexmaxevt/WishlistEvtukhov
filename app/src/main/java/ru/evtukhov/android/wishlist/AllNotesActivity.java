package ru.evtukhov.android.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class AllNotesActivity extends AppCompatActivity {

    private List<Note> noteList = new ArrayList<>();
    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    private void initView() {
        FloatingActionButton floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllNotesActivity.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Note> getNote() {
        try {
            noteList = FileNoteRepository.importFromJSON(this);
        } catch (EmptyStackException e) {
            e.getMessage();
        }
        if (noteList != null) {
            adapter = new NoteAdapter(this, noteList);
            ListView listView = findViewById(R.id.list);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, getText(R.string.app_toastNoteSave), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getText(R.string.app_toastNoteNotSave), Toast.LENGTH_LONG).show();
        }
        return null;
    }
}
