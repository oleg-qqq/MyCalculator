package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.radiobutton.MaterialRadioButton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
public class MainActivity extends BaseActivity{

    //Объявляем переменные
    EditText editText;
    Boolean isNew = true;
    String operator;
    String oldNumber;
    Boolean isDot = true;
    Boolean isPlusMinus = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//Получаем данные из SettingsActivity
        Intent intent = getIntent();
        int them = intent.getIntExtra("theme",0);

        if (intent.hasExtra("theme")) {

            Toast.makeText(this, "Theme has been changed", Toast.LENGTH_SHORT).show();
            setAppTheme(them);
            recreate();
        }

        //Получаем ID вью editText
        editText = findViewById(R.id.editText);
    }

    //Реализуем нажатия на цифры.
    public void clickNumber(View view) {
//Убираем ноль из вью
        if (isNew) {
            editText.setText("");
            isNew = false;
        }
//Присваиваем переменной number текст из вью и приводим к строке
        String number = editText.getText().toString();
//С помощью оператора свитч заполняем переменную number цифрами
        switch (view.getId()) {
            case R.id.button1:
                number = number + "1";
                break;
            case R.id.button2:
                number = number + "2";
                break;
            case R.id.button3:
                number = number + "3";
                break;
            case R.id.button4:
                number = number + "4";
                break;
            case R.id.button5:
                number = number + "5";
                break;
            case R.id.button6:
                number = number + "6";
                break;
            case R.id.button7:
                number = number + "7";
                break;
            case R.id.button8:
                number = number + "8";
                break;
            case R.id.button9:
                number = number + "9";
                break;
            case R.id.button0:
                number = number + "0";
                break;
            case R.id.buttonDot:
                if (isDot) {     //Убираем возможность поставить точку более 1 раза//
                    number = number + ".";
                    isDot = false;
                }
                break;
            case R.id.buttonPlusMinus: //Добавляем возможность менять "-" на "+"
                if (number.charAt(0) == '-') {
                    number = number.substring(1); //Функция оставляет только значения с 1го знака
                } else {
                    number = "-" + number;
                }
                break;
        }
//Выводим во вью editText значение number
        editText.setText(number);
    }

    //Выбираем матем-ое действие
    public void operation(View view) {
// Меняем переменную isNew на true чтобы при наборе следующего числа было установлено во вью пустое значение
        isNew = true;
        isDot = true;
        Boolean isPlusMinus = true;
//В переменную oldNumber записываем число, кот. ввели первым
        oldNumber = editText.getText().toString();
        switch (view.getId()) {
            case R.id.buttonPlus:
                operator = "+";
                break;
            case R.id.buttonMinus:
                operator = "-";
                break;
            case R.id.buttonMil:
                operator = "*";
                break;
            case R.id.buttonDiv:
                operator = "/";
                break;
        }
    }

    //Результат вычисления
    public void clickResult(View view) {
//В строковую переменную newNumber записываем число, кот. ввели ВТОРЫМ после мат. действия
        String newNumber = editText.getText().toString();
        Double result = 0.0;
        switch (operator) {
            case "+":
                result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                break;
            case "-":
                result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                break;
            case "*":
                result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                break;
            case "/":
                result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                break;
        }
        editText.setText(result + "");
    }

    //Добавили функционал кнопки сброса значений
    public void clickC(View view) {
        editText.setText("0");
        isNew = true; // Избавляемся от нуля при следующем вводе цифр
    }

    // Реализовано удаление по одному символу
    public void clickDelete(View view) {
        String number = editText.getText().toString();
        number = number.substring(0, number.length() - 1);

        editText.setText(number);
    }

    public void goSettings(View view) {
        Intent runSettings = new Intent(MainActivity.this, SettingsActivity.class);

        startActivity(runSettings);
    }
}