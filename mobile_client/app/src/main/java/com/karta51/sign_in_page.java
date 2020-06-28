package com.karta51;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class sign_in_page extends AppCompatActivity {

    private EditText number, password, card1, card2, card3, id;
    private Button btn_login;
    //private static String URL_LOGIN = "http://10.1.30.48:80/login.php";
    private static String URL_LOGIN = "http://f0442434.xsph.ru/login.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        sessionManager = new SessionManager(this);

        if (sessionManager.isLogin()){
            finish();
            startActivity(new Intent(sign_in_page.this, personal_account.class));
        }


        number = findViewById(R.id.number);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mNumber = number.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (!mNumber.isEmpty() || !mPass.isEmpty()) {
                    Login(mNumber, mPass);
                } else {
                    number.setError("Введите номер телефона");
                    password.setError("Введите пароль");
                }
            }
        });

    }
            private void Login(final String number, final String password) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String success = jsonObject.getString("success");
                                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                                    if (success.equals("1")) {

                                        for (int i = 0; i< jsonArray.length(); i++) {

                                            JSONObject object = jsonArray.getJSONObject(i);

                                            String id = object.getString("id").trim();
                                            String number = object.getString("phone_number").trim();
                                            String card1 = object.getString("card1").trim();
                                            String card2 = object.getString("card2").trim();
                                            String card3 = object.getString("card3").trim();


                                            sessionManager.createSession(number, card1, card2, card3, id);

                                            Intent intent = new Intent(sign_in_page.this, personal_account.class);
                                            intent.putExtra("number", number);
                                            intent.putExtra("card1", card1);
                                            intent.putExtra("card2", card2);
                                            intent.putExtra("card3", card3);
                                            startActivity(intent);

                                            /*Intent intent = new Intent(getApplicationContext(), personal_account.class);
                                            startActivity(intent);*/


                                        }



                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(sign_in_page.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(sign_in_page.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError{
                        Map<String, String> params = new HashMap<>();
                        params.put("number", number);
                        params.put("password", password);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }



}
