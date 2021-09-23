package com.itca.evalpractica2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

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

    private String[] notas;
    private ListView lv1;
    private ArrayAdapter adaptador;
    ArrayList<String> list;
    ArrayAdapter adapter;


    private EditText et1, et2,et3;
    private Button btn1;
    OpenHelper admin = new OpenHelper(this, "EvalNotas.db", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1=(ListView)findViewById(R.id.lista_notas);


        et1 = findViewById(R.id.IngTitulo);
        et2= findViewById(R.id.IngDes);
        et3=findViewById(R.id.IngAutor);

        btn1 = findViewById(R.id.guardar);

        
        
    }

    public void guardar(View view) {

        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();

        String titulo =et1.getText().toString();
        String descrip = et2.getText().toString();
        String autor = et3.getText().toString();

        registro.put("id", (byte[]) null);
        registro.put("Titulo", titulo);
        registro.put("Descripcion", descrip);
        registro.put("Autor", autor);

        if (titulo.isEmpty()){
            et1.setError("Campo obligatorio");
        }else if (descrip.isEmpty()){
            et2.setError("Campo obligatorio");
        }else if (autor.isEmpty()){
            et3.setError("Campo obligatorio");
        }else {
            bd.insert("tb_Notas", null, registro);
            bd.close();

            et1.setText(null);
            et2.setText(null);
            et3.setText(null);
            Toast.makeText(this, "Nota guardada correctamente", Toast.LENGTH_SHORT).show();
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
}