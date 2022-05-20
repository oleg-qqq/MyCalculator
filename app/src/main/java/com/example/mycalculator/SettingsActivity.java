package com.example.mycalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class SettingsActivity extends BaseActivity {
Integer CodeStyle;
    Context context = SettingsActivity.this;
    Class DestinationActivity = MainActivity.class;
    int newTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initThemeChooser();
    }

//Инициализация радиокнопок
    public void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonDay),
                DayThemeCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonNight),
                NightThemeCodeStyle);
        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton) rg.getChildAt(getCodeStyle(DayThemeCodeStyle))).setChecked(true);
    }

    public void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (codeStyle == 0){
                    Toast.makeText(context, "Now is Day Theme", Toast.LENGTH_SHORT).show();
                    theme(codeStyle);
                }else if (codeStyle == 1){
                    Toast.makeText(context, "Now is Night Theme", Toast.LENGTH_SHORT).show();
                    theme(codeStyle);
                }
//Сохраним настройки
                setAppTheme(codeStyle);
                recreate();
            }
        });
    }
    //Метод передачи темы в MainActivity и переход на главный экран
    public void theme(int codeStyle) {
        newTheme = codeStyle;
        Intent runMain = new Intent(context, MainActivity.class);
        Intent themeIntent = new Intent(context, DestinationActivity);
        themeIntent.putExtra("themeID", newTheme);
        startActivity(runMain);
    }
}