package com.example.eliteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    //Declaramos las variables
    EditText txtUsuario, txtPass;
    Button btnIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Unimos Backend con FrontEnd
        //Primero nuestras variables globales
        txtUsuario  = findViewById(R.id.txt_Usuario);
        txtPass     = findViewById(R.id.txt_Pass);
        btnIniciar  = findViewById(R.id.btn_Iniciar);

        //Creamos el metodo OnClick
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarUsuario("https://spatial-patient.000webhostapp.com/ajax/login.php?op=log"); //Tenemos que ingresar la URL de nuestro php de ValidarUsuario.php
            }
        });

    }

    //Creamos el método validar usuario
    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            //Validamos que el response no esté vacío
                if (!response.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parametros = new HashMap<String,String>();
                parametros.put("clie_usuario",txtUsuario.getText().toString());
                parametros.put("clie_clave",txtPass.getText().toString());
                return super.getParams();
            }
        };
        //Creamos una instancia del Request para realizar la petición
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}