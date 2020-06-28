package com.karta51;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;


public class results extends AppCompatActivity {

    RequestQueue rq;

    TextView idText, cardBalance, cardState, cardDateTo, cardDateFrom;

    String id, series, datefrom, dateto, u_value, url, num, balance;

    int state;

    //String url ="https://karta51.ru/balance/getcardinfo.php?cardnum=0222001359";
    String url1 ="https://karta51.ru/balance/getcardinfo.php?cardnum=0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();

        num = getIntent().getStringExtra("num");
        rq = Volley.newRequestQueue(this);

        //idText = findViewById(R.id.card_id);
        cardBalance = findViewById(R.id.card_balance);


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


