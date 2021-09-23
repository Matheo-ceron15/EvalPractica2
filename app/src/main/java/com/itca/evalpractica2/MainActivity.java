package com.itca.evalpractica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2,et3;
    private Button btn1;
    OpenHelper admin = new OpenHelper(this, "EvalNotas.db", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.IngTitulo);
        et2= findViewById(R.id.IngDes);
        et3=findViewById(R.id.IngAutor);

        btn1 = findViewById(R.id.guardar);
    }

    public void guardar(View view) {
        Toast.makeText(this, "guardado", Toast.LENGTH_SHORT).show();

        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();

        String titulo =et1.getText().toString();
        String descrip = et2.getText().toString();
        String autor = et3.getText().toString();


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
}