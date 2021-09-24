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
    private EditText et1, et2,et3;
    private Button btn1, btn2, btn3;
    OpenHelper admin = new OpenHelper(this, "EvalNotas.db", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_nota);


        et1 = findViewById(R.id.et_titulo);
        et2= findViewById(R.id.et_descripcion);
        et3=findViewById(R.id.et_autor);

        btn1 = findViewById(R.id.btn_borrar);
        btn2 = findViewById(R.id.btn_actualizar);

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
        SQLiteDatabase bd = admin.getWritableDatabase();

        String titulo =et1.getText().toString();


        //METODO ELIMINAR
        if (titulo.isEmpty()) {
            et1.setError("campo obligatorio");
        }else{
            int cant = bd.delete("bt_Notas", "Titulo=" + titulo, null);
            bd.close();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            if (cant == 1){
                Toast.makeText(this, "Se borro el articulocon dicho codigo", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void actualizar(View view) {

        String titulo =et1.getText().toString();
        String descrip = et2.getText().toString();
        String autor = et3.getText().toString();
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();

        //                                          METODO ELIMINAR
        if (titulo.isEmpty()) {
            et1.setError("campo obligatorio");
        }else if (descrip.isEmpty()){
            et2.setError("campo obligatorio");
        }else if (autor.isEmpty()) {
            et3.setError("campo obligatorio");
        }else{
            registro.put("codigo", titulo);
            registro.put("descripcion", descrip);
            registro.put("precio", autor);
            int cant = bd.update("articulos", registro, "Titulo=" + titulo, null);
            bd.close();
            if (cant ==1){
                Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }

    }

//    public void buscarCod(View view) {
//        SQLiteDatabase bd = admin.getWritableDatabase();
//
//        String titulo =et1.getText().toString();
//        String descrip = et2.getText().toString();
//        String autor = et3.getText().toString();
//
//
//        if (titulo.isEmpty()) {
//            et1.setError("campo obligatorio");
//        }else{
//            Cursor fila = bd.rawQuery("select descripcion, precio from articulos where codigo=" + titulo, null);
//            if (fila.moveToFirst()){
//                et2.setText(fila.getString(0));
//                et3.setText(fila.getString(1));
//            }else{
//                Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT).show();
//                bd.close();
//            }
//        }
//
//    }
}