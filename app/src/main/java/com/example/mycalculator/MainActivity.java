package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class MainActivity extends AppCompatActivity {
//Объявляем переменные
    EditText editText;
    Boolean isNew = true;
    String operator;
    String oldNumber;
    Boolean isDot = true;
    Boolean isPlusMinus = true;

    //Имя настроек
    private static final String NameSharedPreference = "LOGIN";
    // Имя параметра в настройках
    private static final String DayTheme = "APP_THEME";
    private static final int DayThemeCodeStyle = 0;
    private static final int NightThemeCodeStyle = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Устанавливать тему надо только до установки макета активити
        setTheme(getAppTheme(R.style.DayTheme));
        setContentView(R.layout.activity_main);
        initThemeChooser();


//Получаем ID вью editText
        editText = findViewById(R.id.editText);

    }
//Реализуем нажатия на цифры.
    public void clickNumber(View view) {
//Убираем ноль из вью
        if (isNew){
            editText.setText("");
            isNew = false;
        }
//Присваиваем переменной number текст из вью и приводим к строке
        String number = editText.getText().toString();
//С помощью оператора свитч заполняем переменную number цифрами
        switch (view.getId()){
            case R.id.button1: number = number+"1"; break;
            case R.id.button2: number = number+"2"; break;
            case R.id.button3: number = number+"3"; break;
            case R.id.button4: number = number+"4"; break;
            case R.id.button5: number = number+"5"; break;
            case R.id.button6: number = number+"6"; break;
            case R.id.button7: number = number+"7"; break;
            case R.id.button8: number = number+"8"; break;
            case R.id.button9: number = number+"9"; break;
            case R.id.button0: number = number+"0"; break;
            case R.id.buttonDot:
                if (isDot){     //Убираем возможность поставить точку более 1 раза//
                    number = number+".";
                    isDot = false;
                }
                break;
            case R.id.buttonPlusMinus: //Добавляем возможность менять "-" на "+"
                if (number.charAt(0) == '-'){
                    number = number.substring(1); //Функция оставляет только значения с 1го знака
                }else {
                    number = "-"+number;
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
        switch (view.getId()){
            case R.id.buttonPlus: operator = "+"; break;
            case R.id.buttonMinus: operator = "-"; break;
            case R.id.buttonMil: operator = "*"; break;
            case R.id.buttonDiv: operator = "/"; break;
        }
    }
//Результат вычисления
    public void clickResult(View view) {
//В строковую переменную newNumber записываем число, кот. ввели ВТОРЫМ после мат. действия
        String newNumber = editText.getText().toString();
        Double result = 0.0;
        switch (operator){
            case "+" : result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber); break;
            case "-" : result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber); break;
            case "*" : result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber); break;
            case "/" : result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber); break;
        }
        editText.setText(result+"");

    }
//Добавили функционал кнопки сброса значений
    public void clickC(View view) {
        editText.setText("0");
        isNew = true; // Избавляемся от нуля при следующем вводе цифр
    }
// Реализовано удаление по одному символу
    public void clickDelete(View view) {
        String number = editText.getText().toString();
        number = number.substring(0,number.length()-1);

        editText.setText(number);
    }


    // Инициализация радиокнопок
   private void initThemeChooser(){
        initRadioButton(findViewById(R.id.radioButtonDay),
                DayThemeCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonNight),
                NightThemeCodeStyle);
        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(DayThemeCodeStyle))).setChecked(true);
    }

    // Все инициализации кнопок очень похожи, поэтому создадим метод для переиспользования
    private void initRadioButton(View button, final int codeStyle){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// сохраним настройки
                setAppTheme(codeStyle);
// пересоздадим активити, чтобы тема применилась
                recreate();
            }
        });
    }


    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }
// Чтение настроек, параметр «тема»
    private int getCodeStyle(int codeStyle){
// Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
//Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(DayTheme, codeStyle);
    }
// Сохранение настроек
    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
// Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(DayTheme, codeStyle);
        editor.apply();
    }
    private int codeStyleToStyleId(int codeStyle){
        switch(codeStyle){
            case DayThemeCodeStyle:
                return R.style.DayTheme;
            case NightThemeCodeStyle:
                return R.style.NightTheme;
            default:
                return R.style.DayTheme;
        }
    }
}