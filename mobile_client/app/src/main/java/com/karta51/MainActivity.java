package com.karta51;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText num;

    private Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        num = findViewById(R.id.editText);
        myButton = findViewById(R.id.button);
        myButton.setOnClickListener(this);
    }

    public void onClick(View v) {
               Intent intent = new Intent(this, results.class);
               intent.putExtra("num", num.getText().toString());
               startActivity(intent);
           }
}



