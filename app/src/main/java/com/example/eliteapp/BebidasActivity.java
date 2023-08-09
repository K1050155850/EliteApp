package com.example.eliteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import cz.msebera.android.httpclient.Header;

public class BebidasActivity extends AppCompatActivity {
EditText txtCantidad,txtCedulaBebidas,txtPrecioBebidas;
Button btnPedirBebida;

//private AsyncHttpClient client;
private Spinner spBebidas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas);
        btnPedirBebida = findViewById(R.id.btn_PedirBebida);
        txtCantidad = findViewById(R.id.txt_CantidadBebida);
        /*
        txtPrecioBebidas = findViewById(R.id.txt_PrecioBebidas);
        txtCedulaBebidas = findViewById(R.id.txt_CedulaBebidas);

        btnPedirBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularYMostrarPrecioTotal();
                enviarDatosPorPost();

            }
        });

        client = new AsyncHttpClient();
        spBebidas = (Spinner) findViewById(R.id.cmb_Bebidas);
        LlenarSpinner();

    }

    //Creamos el metodo para calcular el precio
    private void calcularYMostrarPrecioTotal() {
        if (spBebidas.getSelectedItem() != null) {
            Bebidas selectedBebida = (Bebidas) spBebidas.getSelectedItem();
            float precioUnitario = selectedBebida.getPrecio();

            String cantidadString = txtCantidad.getText().toString();

            if (cantidadString.isEmpty()) {
                Toast.makeText(this, "Ingresa una cantidad válida", Toast.LENGTH_SHORT).show();
                return;
            }

            int cantidad = Integer.parseInt(cantidadString);
            float precioTotal = precioUnitario * cantidad;

            txtPrecioBebidas.setText(String.valueOf(precioTotal));

            // Construir el objeto con la información a enviar
            Map<String, String> params = new HashMap<>();
            params.put("prod_id", String.valueOf(selectedBebida.getId()));
            params.put("det_cantidad", cantidadString);
            params.put("det_precioT", String.valueOf(precioTotal));
            params.put("clie_cedula", txtCedulaBebidas.getText().toString());

            // Envía los datos usando Volley o la biblioteca que estás utilizando

        }
    }


    //Creamos el método para enviar los datos por metodo POST
    private void enviarDatosPorPost() {
        Bebidas selectedBebida = (Bebidas) spBebidas.getSelectedItem();
        if (selectedBebida == null) {
            return;
        }

        String cantidadString = txtCantidad.getText().toString().trim();
        if (cantidadString.isEmpty()) {
            return;
        }

        int cantidad = Integer.parseInt(cantidadString);
        float precioUnitario = selectedBebida.getPrecio();
        float precioTotal = precioUnitario * cantidad;

        Map<String, String> params = new HashMap<>();
        params.put("prod_id", String.valueOf(selectedBebida.getId()));
        params.put("det_cantidad", cantidadString);
        params.put("det_precioT", String.valueOf(precioTotal));
        params.put("clie_cedula", txtCedulaBebidas.getText().toString());

        // Utiliza los datos construidos para el cuerpo del POST
        String url = "https://spatial-patient.000webhostapp.com/Pedido.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Manejar la respuesta exitosa si es necesario
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error si ocurre
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        // Agregar la solicitud a la cola
        requestQueue.add(stringRequest);
    }






//Metodo para llenar el spinner
    private void LlenarSpinner(){
        String URL="https://spatial-patient.000webhostapp.com/Productos.php";
        client.post(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    cargarSpinner(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    //Metodo SetUp Spinner Listener
    private void setupSpinnerListener() {
        spBebidas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Bebidas selectedBebida = (Bebidas) parentView.getItemAtPosition(position);
                String selectedProductId = selectedBebida.getProducto();
                String selectedProductPrice = String.valueOf(selectedBebida.getPrecio());

                EditText txtCantidad = findViewById(R.id.txt_CantidadBebida);
                txtCantidad.setText(selectedProductPrice); // Coloca el precio en el EditText
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Si no se selecciona nada
            }
        });
    }


    //Creamos el método cargarSpinner
    private void cargarSpinner(String respuesta){
        ArrayList<Bebidas>lista = new ArrayList<Bebidas>();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);
            for (int i=0; i < jsonArray.length(); i++){
                Bebidas bebidas = new Bebidas();
                bebidas.setProducto(jsonArray.getJSONObject(i).getString("prod_nombre"));
                bebidas.setPrecio(Float.parseFloat(jsonArray.getJSONObject(i).getString("prod_precio")));
                lista.add(bebidas);
            }
            ArrayAdapter<Bebidas> bebida = new ArrayAdapter<Bebidas>(this, android.R.layout.simple_dropdown_item_1line, lista);
            spBebidas.setAdapter(bebida);

        } catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
