package com.example.eliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    EditText et_cedula, et_nombres, et_direccion, et_celular, et_usuario, et_clave;
    Button b_insertar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et_cedula=findViewById(R.id.txt_Cedula);
        et_nombres=findViewById(R.id.txt_Nombre);
        et_direccion=findViewById(R.id.txt_Direccion);
        et_celular=findViewById(R.id.txt_telefono);
        et_usuario=findViewById(R.id.txt_Correo);
        et_clave=findViewById(R.id.txt_Contrasenia);

        b_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarCliente("https://spatial-patient.000webhostapp.com/ajax/ApiRegistroCliente.php?op=apiInsertClie");
            }
        });
    }

    public void insertarCliente(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE", response);

                try {

                    boolean recibeRespuesta = Boolean.parseBoolean(response);

                    if (recibeRespuesta) {
                        // Inicio exitoso
                        Toast.makeText(getApplicationContext(), "Registro exitoso" + Log.d("RESPONSE", response), Toast.LENGTH_SHORT).show();
                        //Ir al login
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        // Inicio de sesión fallido
                        Toast.makeText(getApplicationContext(), "Registro fallido" + Log.d("RESPONSE", response), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Error al analizar la respuesta JSON
                    Toast.makeText(getApplicationContext(), "Error en la respuesta del servidor" + e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en la conexión" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();

                //Asignacion
                String cedula = et_cedula.getText().toString();
                String nombres = et_nombres.getText().toString();
                String direccion = et_direccion.getText().toString();
                String telefono = et_celular.getText().toString();
                String correo = et_usuario.getText().toString();
                String clave = et_clave.getText().toString();

                //Envío de parámetros POST a recepcionar en la consulta
                parametros.put("cedula", cedula);
                parametros.put("nombres", nombres);
                parametros.put("direccion", direccion);
                parametros.put("celular", telefono);
                parametros.put("correo", correo);
                parametros.put("clave", clave);

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void irLogin(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}