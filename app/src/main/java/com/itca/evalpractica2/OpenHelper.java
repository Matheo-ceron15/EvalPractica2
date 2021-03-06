package com.itca.evalpractica2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper {


    public OpenHelper(@Nullable Context context) {
        super(context, "EvalNotas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table tb_Notas (id INTEGER PRIMARY KEY, Titulo text, Descripcion text, Autor text)");
//        bd.execSQL("Insert into tb_Notas values(Null,"","","")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        bd.execSQL("drop table if exists tb_Notas");
        onCreate(bd);
    }
}
