package com.itca.evalpractica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText titulo, desc, autor;



    private EditText et1, et2,et3;
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        titulo = findViewById(R.id.IngTitulo);
        desc = findViewById(R.id.IngDes);
        autor = findViewById(R.id.IngAutor);
        
    }

    public void guardar(View view) {


        try {
            OpenHelper conexion = new OpenHelper(this);
            SQLiteDatabase bd = conexion.getWritableDatabase();
            String t = titulo.getText().toString();
            String d = desc.getText().toString();
            String a = autor.getText().toString();

            ContentValues registro = new ContentValues();
            registro.put("id", (Integer) null);
            registro.put("Titulo", t);
            registro.put("Descripcion", d);
            registro.put("Autor", a);

            int result = (int) bd.insert("tb_Notas", null, registro);
            bd.close();

            if (result > 0) {
                Toast.makeText(this, "Se guardo el registro", Toast.LENGTH_SHORT).show();
                titulo.setText("");
                desc.setText("");
                autor.setText("");
            } else {
                Toast.makeText(this, "No Se guardo el registro", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            String msg = e.toString();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.accion_lista) {
            Intent intent = new Intent(MainActivity.this, Listado.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void autores(View view) {
        Toast.makeText(this, "Acion no disponible", Toast.LENGTH_SHORT).show();
    }

    public void listado(View view) {
        Intent intent= new Intent(MainActivity.this, Listado.class);
        startActivity(intent);
    }
}