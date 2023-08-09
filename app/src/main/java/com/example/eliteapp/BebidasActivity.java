package com.example.eliteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class BebidasActivity extends AppCompatActivity {
EditText txtCantidad;
Spinner spBebidas;
Button btnPedirBebida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas);

        txtCantidad  = findViewById(R.id.txt_CantidadBebida);
        spBebidas     = findViewById(R.id.cmb_Bebidas);
        btnPedirBebida  = findViewById(R.id.btn_PedirBebida);

        btnPedirBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PedirBebida(""); //Tenemos que ingresar la URL de nuestro php de ValidarUsuario.php
            }
        });

    }

    private void PedirBebida(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Validamos que el response no esté vacío
                if (!response.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(BebidasActivity.this,"No se pudo registrar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BebidasActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parametros = new HashMap<String,String>();
                parametros.put("det_cantidad",txtCantidad.getText().toString());
                parametros.put("prod_id",spBebidas.getSelectedItem().toString());
                return super.getParams();
            }
        };
        //Creamos una instancia del Request para realizar la petición
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}