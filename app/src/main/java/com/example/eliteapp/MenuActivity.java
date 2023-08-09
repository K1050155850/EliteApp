package com.example.eliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void Bebidas(View view){
        Intent intent = new Intent(this, BebidasActivity.class);
        startActivity(intent);
    }

    public void Cocteles(View view){
        Intent intent = new Intent(this, CoctelesActivity.class);
        startActivity(intent);
    }
}