package ru.evtukhov.android.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
    private View circle1;
    private View circle2;
    private View circle3;
    private View circle4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);
        initView();
    }

    // Инициализируем переменные
    private void initView () {
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
    }

    // Устанавливаем цвет кружков при вводе цифры
    private void setColorCircle () {

    }
}
