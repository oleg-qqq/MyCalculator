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


//Сохраним настройки и передадим в MainActivity информацию о теме + сделаем переход на главный экран
                setAppTheme(codeStyle);
                recreate();
                //String cod = String.valueOf(codeStyle);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("theme", codeStyle);
                startActivity(intent);



            }
        });
    }

}