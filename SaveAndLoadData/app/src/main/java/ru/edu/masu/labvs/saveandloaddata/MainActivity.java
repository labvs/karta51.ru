package ru.edu.masu.labvs.saveandloaddata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edCardNumber;
    Button btnSave;
    Button btnLoad;
    // Штука для сохранения и загрузки настроек приложения
    SharedPreferences sPref;
    // Это строка (CARD_NUMBER), которую будем сохранять и загружать
    final String CARD_NUMBER = "1234567890";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ищем виджеты и привязываем к переменным, объявленным ранее
        edCardNumber = (EditText) findViewById(R.id.edCardNumber);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnLoad = (Button) findViewById(R.id.btnLoad);

        // Создаем обработчики кликов по кнопкам
        View.OnClickListener btnSaveOnClic = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //
                sPref = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(CARD_NUMBER, edCardNumber.getText().toString());
                ed.commit();
            }
        };

        View.OnClickListener btnLoadOnClic = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //
                sPref = getPreferences(MODE_PRIVATE);
                String savedText = sPref.getString(CARD_NUMBER, "");
                edCardNumber.setText(savedText);
            }
        };

        // Привязываем обработчики кликов к самим кнопкам
        btnSave.setOnClickListener(btnSaveOnClic);
        btnLoad.setOnClickListener(btnLoadOnClic);

    }
}
