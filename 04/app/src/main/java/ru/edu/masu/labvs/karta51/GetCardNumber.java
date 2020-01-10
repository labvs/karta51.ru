package ru.edu.masu.labvs.karta51;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class GetCardNumber extends AppCompatActivity {

    RequestQueue rq;

    TextView idText, cardBalance, cardState, cardDateTo, cardDateFrom;

    String id, series, datefrom, dateto, u_value, url, num, balance;

    int state;

    //String url ="https://karta51.ru/balance/getcardinfo.php?cardnum=0222001359";
    String url1 ="https://karta51.ru/balance/getcardinfo.php?cardnum=0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        num = getIntent().getStringExtra("num");
        rq = Volley.newRequestQueue(this);

        //idText = findViewById(R.id.card_id);
        cardBalance = findViewById(R.id.txtBalance);


        url = url1 + num;
        sendjsonrequest();
    }




    public void sendjsonrequest () {

        JsonObjectRequest JsonObjectRequest = new  JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //id = response.getString("id");
                    balance = response.getString("ep_balance_fact");

                    //idText.setText("№ " + id);
                    cardBalance.setText(balance);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse( VolleyError error) {

            }

        });

        rq.add(JsonObjectRequest);
    }
}
