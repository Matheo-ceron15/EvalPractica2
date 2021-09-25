package com.itca.evalpractica2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Listado extends Activity {
    public ListView list;
    public TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        list = findViewById(R.id.lista_notas);

        ArrayList<String> valor = new ArrayList<>();


        OpenHelper conexion = new OpenHelper(this);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        Cursor fila = bd.rawQuery("select titulo from tb_Notas", null);
        if (fila.moveToFirst()) {
            do {
                valor.add(fila.getString(0));
            } while (fila.moveToNext());
        }
        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, valor);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(view.getContext(), VistaNota.class);
                //String t = valor.get(position);
                String t = (String) list.getItemAtPosition(position);

                intent.putExtra("valorTitle", t);
                startActivity(intent);
            }
        });

    }
    public void regresar(View view) {
        Intent intent = new Intent(Listado.this, MainActivity.class);
        startActivity(intent);
    }

}