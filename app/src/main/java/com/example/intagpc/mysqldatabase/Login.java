package com.example.intagpc.mysqldatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private static final String TAG = "Login";
    EditText etUserEmail, etUserPassword;
    Button btnLogin;
    String studentEmail, studentPassword;

    private static final String singInURL = "http://pc-ip-address/StudentsDB/getStudents.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidgets();
        btnLoginClickListener();
    }

    private void btnLoginClickListener() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentEmail = etUserEmail.getText().toString();
                studentPassword = etUserPassword.getText().toString();

                studentLogin(studentEmail, studentPassword);
            }
        });
    }

    private void studentLogin(final String studentEmail, final String studentPassword) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, singInURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d(TAG, "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onErrorResponse: " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> paramaters = new HashMap<>();
                paramaters.put("email", studentEmail);
                paramaters.put("password", studentPassword);
                return paramaters;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        requestQueue.add(stringRequest);
    }


    private void initWidgets() {

        etUserEmail = findViewById(R.id.etUserEmail);
        etUserPassword = findViewById(R.id.etUserPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }
}
