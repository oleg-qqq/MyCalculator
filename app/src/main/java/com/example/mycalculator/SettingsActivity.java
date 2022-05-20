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

//Сохраним настройки
                setAppTheme(codeStyle);
//                Intent themeIntent = new Intent(context, DestinationActivity);
//                themeIntent.putExtra("themeID", codeStyle);
                recreate();
//                Toast.makeText(context, codeStyle, Toast.LENGTH_SHORT).show();

//                Toast.makeText(context, codeStyle, Toast.LENGTH_LONG).show();

            }
        });
    }

    public void ButtonReturn(View view) {
        Toast.makeText(context, "Return", Toast.LENGTH_SHORT).show();
        Intent runMain = new Intent(context, MainActivity.class);
        startActivity(runMain);
    }
}