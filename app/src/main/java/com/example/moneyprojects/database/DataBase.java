package com.example.moneyprojects.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public static final String ELEMENTOS_TABLE = "ELEMENTOS";
    public static final String OBRAS_TABLE = "OBRAS";
    public static final String DATE_TABLE = "DATE";

    public static final String ID_FIELD = "ID";
    public static final String CLASIFICACION_FIELD = "clasificacion";
    public static final String COSTO_FIELD = "costo";
    public static final String NAME_FIELD = "name";
    public static final String TIPO_FIELD = "tipo";
    public static final String DOCUMENTO_FIELD = "documento";
    public static final String OBRA_FIELD = "obra";
    public static final String ELEMENTO_FIELD = "elemento";
    public static final String FECHA_FIELD = "fecha";
    public static final String NOMINA_FIELD = "nomina";
    public static final String GRUA_TIPO = "GRUA";
    public static final String CUADRILLA_TIPO = "CUADRILLA";

    public static String SCHEMA = "MONEYPROJECTQUERY";

    String elementosTable = "CREATE TABLE " + ELEMENTOS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_FIELD + " TEXT, " + CLASIFICACION_FIELD + " TEXT, " + COSTO_FIELD + " NUMERIC, " + TIPO_FIELD + " TEXT, " + NOMINA_FIELD + " NUMERIC)";

    String obrasTable = "CREATE TABLE " + OBRAS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_FIELD + " TEXT, " + DOCUMENTO_FIELD + " TEXT)";

    String dateTable  = "CREATE TABLE " + DATE_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + OBRA_FIELD + " INTEGER, " + ELEMENTO_FIELD + " INTEGER, " + FECHA_FIELD + " NUMERIC)";

    public DataBase(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(elementosTable);
        db.execSQL(obrasTable);
        db.execSQL(dateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                //hola
        }
    }
}
