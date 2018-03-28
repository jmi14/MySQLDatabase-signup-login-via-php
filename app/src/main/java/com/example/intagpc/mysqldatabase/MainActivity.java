package com.example.intagpc.mysqldatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPassword;
    private Button btnSignUp;
    private TextView tvLogin;
    private String stName, stEmail, stPassword;
    private static final String signUpURL = "http://pc-ip-address/StudentsDB/registration.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        btnSignUpClickListener();
        tvLoginClickListener();
    }

    private void tvLoginClickListener() {

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent
                        = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void btnSignUpClickListener() {

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stName = etName.getText().toString();
                stEmail = etEmail.getText().toString();
                stPassword = etPassword.getText().toString();
                signUpStudent(stName, stEmail, stPassword);

            }
        });
    }

    private void signUpStudent(final String stName, final String stEmail, final String stPassword) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, signUpURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> paramaters = new HashMap<>();
                paramaters.put("name", stName);
                paramaters.put("email", stEmail);
                paramaters.put("password", stPassword);
                return paramaters;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

    private void initWidgets() {

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);
    }
}
