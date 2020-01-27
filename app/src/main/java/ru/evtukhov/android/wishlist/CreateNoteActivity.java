package ru.evtukhov.android.wishlist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText dateDeadline;
    private Calendar dateAndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        setTitle(R.string.app_newNotesTitle);
        addButtonBack();
        initView();
    }

    // Добавление кнопки "Сохранение" в toolbar activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    // Действия кнопок "Сохранить" и "Назад" в toolbar
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }

        if (item.getItemId() == R.id.action_note) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Инициализация компонентов view
    private void initView () {
        dateDeadline= findViewById(R.id.dateDeadline);
        dateAndTime = Calendar.getInstance();
    }

    // Добавление кнопки "Назад" в toolbar
    private void addButtonBack() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // Диалоговое окно для выбора даты
    public void setDate (View view) {
        new DatePickerDialog(
            this,
            dateSet,
            dateAndTime.get(Calendar.YEAR),
            dateAndTime.get(Calendar.MONTH),
            dateAndTime.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    // Обработчик выбора даты
    DatePickerDialog.OnDateSetListener dateSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month = month + 1;
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, month);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String formattedMonth = "" + month;
            String formattedDayOfMonth = "" + dayOfMonth;
            if(month < 10){
                formattedMonth = "0" + month;
            }
            if(dayOfMonth < 10){
                formattedDayOfMonth = "0" + dayOfMonth;
            }
            String formattedDate = year + "-" + formattedMonth + "-" + formattedDayOfMonth;
            dateDeadline.setText(formattedDate);
        }
    };
}
