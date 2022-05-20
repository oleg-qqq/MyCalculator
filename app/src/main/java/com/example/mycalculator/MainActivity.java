package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BaseActivity{


    EditText editText;
    Boolean isNew = true;
    String operator = "";
    String oldNumber;
    Boolean ID = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //if(ID) {
            ID = false;
            //Получаем данные из SettingsActivity
            Intent intent = getIntent();
            int them = intent.getIntExtra("theme", 0);

            if (intent.hasExtra("theme")) {

                 Toast.makeText(this, R.string.ThemeChangeToast, Toast.LENGTH_SHORT).show();
                setAppTheme(them);
                recreate();
            }
       // }
    //Получаем ID вью editText
        editText = findViewById(R.id.editText);
    }

    //Реализуем нажатия на цифры.
    public void clickNumber(View view) {
    //Убираем ноль из Вью
        if (isNew) {
            editText.setText("");
            isNew = false;
        }
    //Присваиваем переменной number текст из вью и приводим к строке
        String number = editText.getText().toString();
    //С помощью оператора свитч заполняем переменную number цифрами + доп условия для проверки
        switch (view.getId()) {
            case R.id.button1:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "1";
                break;
            case R.id.button2:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "2";
                break;
            case R.id.button3:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "3";
                break;
            case R.id.button4:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "4";
                break;
            case R.id.button5:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "5";
                break;
            case R.id.button6:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "6";
                break;
            case R.id.button7:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "7";
                break;
            case R.id.button8:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "8";
                break;
            case R.id.button9:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "9";
                break;
            case R.id.button0:
                if (zeroIsFirst(number) && number.length() == 1){
                    number = "0";
            }else{

                number = number + "0";
            }
                break;
            case R.id.buttonDot:
                if (dotIsPresent(number)) {     //Убираем возможность поставить точку более 1 раза//

                }else if (zeroIsFirst(number)){
                    number = "0.";
                }else {
                    number = number + "";
                }
                break;
            case R.id.buttonPlusMinus: //Добавляем возможность менять "-" на "+"
               if (numberIsZero(number)){
                    number = "0";
               }else {

                   if (number.charAt(0) == '-') {
                       number = number.substring(1); //Функция оставляет только значения с 1го знака
                   } else {
                       number = "-" + number;
                   }
               }
                break;

        }
    //Выводим во вью editText значение number
        editText.setText(number);
    }
    //Определяем, первое число ноль?
    public boolean zeroIsFirst(String number) {

        if (number.equals("")) {
            return true;
        }
        if (number.charAt(0) == '0'){
            return true;
        }else {
            return false;
        }
    }

    private boolean numberIsZero(String number) {
        if (number.equals("0") || number.equals("")){
            return true;
        }else {
            return false;
        }
    }

    //Выбираем матем-ое действие
    public void operation(View view) {
// Меняем переменную isNew на true чтобы при наборе следующего числа было установлено во вью пустое значение
        isNew = true;
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

        if ((Double.parseDouble(newNumber) == 0 || newNumber.equals("")) && operator == "/") {
            Toast.makeText(this, R.string.ErrorToast, Toast.LENGTH_LONG).show();
        } else {

        switch (operator) {
            case "+": result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber); break;
            case "-": result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber); break;
            case "*": result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber); break;
            case "/": result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber); break;
        }
        editText.setText(result + "");
        }
    }

    //Функционал кнопки сброса значений
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

    //Intent для перехода к настройкам
    public void goSettings(View view) {
        Intent runSettings = new Intent(MainActivity.this, SettingsActivity.class);

        startActivity(runSettings);
    }
    private boolean dotIsPresent (String number){
        if (number.indexOf(".") == -1){
            return false;
        }else{
            return true;
        }
    }

    //Функционал кнопки Процент
    public void clickPercent(View view) {

        if (operator == ""){
            String number = editText.getText().toString();
            double temp = Double.parseDouble(number) / 100;
            number = temp+"";
            editText.setText(number);
        }else {
            Double result = 0.0;
            String newNumber = editText.getText().toString();
            switch (operator) {
                case "+": result = Double.parseDouble(oldNumber) + Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100; break;
                case "-": result = Double.parseDouble(oldNumber) - Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100; break;
                case "*": result = Double.parseDouble(oldNumber) *  Double.parseDouble(newNumber) / 100; break;
                case "/": result = Double.parseDouble(oldNumber) /  Double.parseDouble(newNumber) * 100; break;
            }
            editText.setText(result+"");
            operator = "";
        }
    }
}