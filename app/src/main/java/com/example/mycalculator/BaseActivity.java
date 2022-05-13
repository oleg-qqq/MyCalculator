package com.example.mycalculator;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    //Имя настроек
    private static final String NameSharedPreference = "LOGIN";
    // Имя параметра в настройках
    private static final String DayTheme = "APP_THEME";
    protected static final int DayThemeCodeStyle = 0;
    protected static final int NightThemeCodeStyle = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Устанавливать тему надо только до установки макета активити
        setTheme(getAppTheme(R.style.DayTheme));
    }

    private int getAppTheme(int codeStyle) {

        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }
    // Чтение настроек, параметр «тема»
    protected int getCodeStyle(int codeStyle){
// Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
//Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(DayTheme, codeStyle);
    }
    // Сохранение настроек
    protected void setAppTheme(int codeStyle) {
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
