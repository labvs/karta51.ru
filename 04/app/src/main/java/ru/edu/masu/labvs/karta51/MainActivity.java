package ru.edu.masu.labvs.karta51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


// 222010125

public class MainActivity extends AppCompatActivity {

   EditText txtCardNumber;
   TextView txtBalance;
   Button btnCheckBalance;
   SharedPreferences sPref;
   final String CARD_NUMBER = "1234567890";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCardNumber = (EditText) findViewById(R.id.txtCardNumber);
        txtBalance = (TextView) findViewById(R.id.txtBalance);
        btnCheckBalance = (Button) findViewById(R.id.btnCheckBalance);

        btnCheckBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCardNumber();
                checkBalance();
            }
        });

        loadCardNumber();
    }

    void saveCardNumber() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(CARD_NUMBER, txtCardNumber.getText().toString());
        ed.commit();
        // Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    void loadCardNumber() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(CARD_NUMBER, "");
        txtCardNumber.setText(savedText);
    }

    String getMinutes(){
        String currentTime = new SimpleDateFormat("mm", Locale.getDefault()).format(new Date());
        return currentTime;
    }

    void checkBalance(){
        //btnCheckBalance.setText(getMinutes());
        Intent intent = new Intent(this, GetCardNumber.class);
        intent.putExtra("num", txtBalance.getText().toString());
        startActivity(intent);
    }


}
