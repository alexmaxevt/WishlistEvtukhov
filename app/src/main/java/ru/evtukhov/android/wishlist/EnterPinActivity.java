package ru.evtukhov.android.wishlist;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EnterPinActivity extends AppCompatActivity {

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private ImageButton backspace;
    private ImageView circle1;
    private ImageView circle2;
    private ImageView circle3;
    private ImageView circle4;
    private String btnNum = "";
    private int circleNum = 0;
    private final static int LENCIRCLE = 4;
    private TextView error;
    private FileKeystore fileKeystore = (FileKeystore) App.getKeystore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);
        initView();
        verifityFile();
        pinButton();
    }

    // Инициализируем переменные
    private void initView() {
        one = findViewById(R.id.numOne);
        two = findViewById(R.id.numTwo);
        three = findViewById(R.id.numThree);
        four = findViewById(R.id.numFour);
        five = findViewById(R.id.numFive);
        six = findViewById(R.id.numSix);
        seven = findViewById(R.id.numSeven);
        eight = findViewById(R.id.numEight);
        nine = findViewById(R.id.numNine);
        zero = findViewById(R.id.numZero);
        backspace = findViewById(R.id.backspace);
        circle1 = findViewById(R.id.num1);
        circle2 = findViewById(R.id.num2);
        circle3 = findViewById(R.id.num3);
        circle4 = findViewById(R.id.num4);
        error = findViewById(R.id.pinError);
    }

    // Устанавливаем цвет кружков при вводе цифры
    private void setColorCircle() {
        switch (circleNum) {
            case 0:
                circle1.setImageResource(R.drawable.shape);
                circle2.setImageResource(R.drawable.shape);
                circle3.setImageResource(R.drawable.shape);
                circle4.setImageResource(R.drawable.shape);
                break;
            case 1:
                circle1.setImageResource(R.drawable.shape_entered);
                circle2.setImageResource(R.drawable.shape);
                circle3.setImageResource(R.drawable.shape);
                circle4.setImageResource(R.drawable.shape);
                break;
            case 2:
                circle1.setImageResource(R.drawable.shape_entered);
                circle2.setImageResource(R.drawable.shape_entered);
                circle3.setImageResource(R.drawable.shape);
                circle4.setImageResource(R.drawable.shape);
                break;
            case 3:
                circle1.setImageResource(R.drawable.shape_entered);
                circle2.setImageResource(R.drawable.shape_entered);
                circle3.setImageResource(R.drawable.shape_entered);
                circle4.setImageResource(R.drawable.shape);
                break;
            case 4:
                circle1.setImageResource(R.drawable.shape_entered);
                circle2.setImageResource(R.drawable.shape_entered);
                circle3.setImageResource(R.drawable.shape_entered);
                circle4.setImageResource(R.drawable.shape_entered);
                break;
        }
    }

    // Устанавливаем слушатели для кнопок
    private void pinButton() {
        one.setOnClickListener(getPinButtons(R.string.app_numOne));
        two.setOnClickListener(getPinButtons(R.string.app_numTwo));
        three.setOnClickListener(getPinButtons(R.string.app_numThree));
        four.setOnClickListener(getPinButtons(R.string.app_numFour));
        five.setOnClickListener(getPinButtons(R.string.app_numFive));
        six.setOnClickListener(getPinButtons(R.string.app_numSix));
        seven.setOnClickListener(getPinButtons(R.string.app_numSeven));
        eight.setOnClickListener(getPinButtons(R.string.app_numEight));
        nine.setOnClickListener(getPinButtons(R.string.app_numNine));
        zero.setOnClickListener(getPinButtons(R.string.app_numZero));
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circleNum > 0) {
                    btnNum = btnNum.substring(0, btnNum.length() - 1);
                    setColorCircle();
                }
            }
        });
    }

    // Слушатель для цифровых кнопок
    private View.OnClickListener getPinButtons(@StringRes final int numberResource) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum += numberResource;
                circleNum++;
                setColorCircle();
                verifyNum();
            }

            ;
        };
    }

    // Проверка количества введенных символов
    private void verifyNum() {
        if (circleNum == LENCIRCLE) {
            verifyPin();
        }
    }

    // Проверка введенного пин-кода
    private void verifyPin() {
        String pin = new FileKeystore(this).readFilePass();
        assert pin != null;
        if (pin.equals(btnNum)) {
            circleNum = 0;
            Intent intent = new Intent(this, AllNotesActivity.class);
            startActivity(intent);
        } else {
            circleNum = 0;
            error.setText(R.string.app_pinError);
        }
    }

    // Проверка наличия файла с пин-кодом
    private void verifityFile() {
        if (!fileKeystore.hasPin()) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }
    }
}
