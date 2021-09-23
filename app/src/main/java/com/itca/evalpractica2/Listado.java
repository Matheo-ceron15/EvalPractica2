package com.itca.evalpractica2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Listado extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

    }
    public void regresar(View view) {
        Intent intent = new Intent(Listado.this, MainActivity.class);
        startActivity(intent);
    }

}