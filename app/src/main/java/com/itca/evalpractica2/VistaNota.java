package com.itca.evalpractica2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VistaNota extends Activity {
    public String x;
    public EditText v;
    public EditText titulo, desc, autor;
    OpenHelper admin = new OpenHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_nota);

        titulo = findViewById(R.id.et_titulo);
        desc = findViewById(R.id.et_descripcion);
        autor = findViewById(R.id.et_autor);

        Bundle bundle = new Bundle();

        String dato = getIntent().getStringExtra("valorTitle");

        v = findViewById(R.id.et_titulo);
        v.setText(dato);

        x = v.getText().toString();


        try {

            OpenHelper conexion = new OpenHelper(this);
            SQLiteDatabase bd = conexion.getWritableDatabase();

            Cursor fila = bd.rawQuery("select Descripcion, Autor from tb_Notas where Titulo = '" + x + "'"  , null);
            if(fila.moveToFirst()) {
                desc.setText(fila.getString(0));
                autor.setText(fila.getString(1));
            }


        } catch (Exception e) {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }

    }


    public void inicio(View view) {
        Intent intent = new Intent(VistaNota.this, MainActivity.class);
        startActivity(intent);

    }

    public void regresar(View view) {
        Intent intent = new Intent(VistaNota.this, Listado.class);
        startActivity(intent);

    }

    public void borrar(View view) {
        OpenHelper conexion = new OpenHelper(this);
        SQLiteDatabase bd = conexion.getWritableDatabase();
        String t = titulo.getText().toString();
        int cant = bd.delete("tb_Notas", "Titulo = '" + x + "'", null);
        bd.close();
        titulo.setText("");
        desc.setText("");
        autor.setText("");

        Toast.makeText(this, "Nota borrada satisfactoriamente", Toast.LENGTH_SHORT).show();

    }

    public void actualizar(View view) {

        OpenHelper conexion = new OpenHelper(this);
        SQLiteDatabase bd = conexion.getWritableDatabase();
        String t = titulo.getText().toString();
        String d = desc.getText().toString();
        String a = autor.getText().toString();


        ContentValues registro = new ContentValues();
        registro.put("Titulo", t);
        registro.put("Descripcion", d);
        registro.put("Autor", a);

        int cant = bd.update("tb_Notas", registro, "Titulo = '" + x + "'", null);
        bd.close();
        if (cant == 1) {
            Toast.makeText(this, "Nota actualizada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al actulizar la nota", Toast.LENGTH_SHORT).show();
        }
    }

}