package ru.evtukhov.android.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SettingActivity extends AppCompatActivity {

    private EditText newPass;
    private ImageButton hideOrView;
    private boolean isVisible;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle(R.string.app_settingTitle);
        initView();
    }

    // Вызов меню для кнопки возврата на предыдущий экран
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    // Обработка нажатия кнопки возврата на предыдущий экран
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings) {
            finish();
        }
        return true;
    }

    // Инициализвция элементов View
    private void initView () {
        newPass = findViewById(R.id.newPass);
        hideOrView = findViewById(R.id.hideOrView);
        hideOrView.setOnClickListener(showOrHide);
        isVisible = false;
        save = findViewById(R.id.saveSetting);
        save.setOnClickListener(saveButton);
    }

    // Действия кнопки показа/скрытия пароля в настройках
    View.OnClickListener showOrHide = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isVisible) {
                hideOrView.setImageResource(R.drawable.ic_hide);
                newPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                newPass.setSelection(newPass.length());
                isVisible = false;
            }
            else {
                hideOrView.setImageResource(R.drawable.ic_view);
                newPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                newPass.setSelection(newPass.length());
                isVisible = true;
            }
        }
    };

    // Сохранение пароля в SharedPreferences
    View.OnClickListener saveButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
