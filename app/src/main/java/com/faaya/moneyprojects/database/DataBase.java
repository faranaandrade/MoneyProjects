package com.faaya.moneyprojects.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public static final String ELEMENTOS_TABLE = "ELEMENTOS";
    public static final String OBRAS_TABLE = "OBRAS";
    public static final String CALENDAR_TABLE = "DATE";

    public static final String ID_FIELD = "ID";
    public static final String CLASIFICACION_FIELD = "clasificacion";
    public static final String COSTO_FIELD = "costo";
    public static final String NAME_FIELD = "name";
    public static final String TIPO_FIELD = "tipo";
    public static final String DOCUMENTO_FIELD = "documento";
    public static final String FINISHED_FIELD = "terminada";
    public static final String OBRA_FIELD = "obra";
    public static final String ELEMENTO_FIELD = "elemento";
    public static final String FECHA_FIELD = "fecha";
    public static final String NOMINA_FIELD = "nomina";
    public static final String GRUA_TIPO = "GRUA";
    public static final String CUADRILLA_TIPO = "CUADRILLA";
    public static final String HIDE_FIELD = "hide";

    public static String SCHEMA = "MONEYPROJECTQUERY";

    String elementosTable = "CREATE TABLE " + ELEMENTOS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_FIELD + " TEXT, " + CLASIFICACION_FIELD + " TEXT, " + COSTO_FIELD + " NUMERIC, " + TIPO_FIELD + " TEXT, " + NOMINA_FIELD + " NUMERIC)";

    String obrasTable = "CREATE TABLE " + OBRAS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_FIELD + " TEXT, " + DOCUMENTO_FIELD + " TEXT, " + FINISHED_FIELD + " NUMERIC)";

    String dateTable = "CREATE TABLE " + CALENDAR_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + OBRA_FIELD + " INTEGER, " + ELEMENTO_FIELD + " INTEGER, " + FECHA_FIELD + " NUMERIC, " + COSTO_FIELD + " NUMERIC)";

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
        System.out.println("old:" + oldVersion);
        System.out.println("new:" + newVersion);
        if (newVersion <= 10) {
            db.execSQL("DROP TABLE IF EXISTS " + OBRAS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + ELEMENTOS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CALENDAR_TABLE);
            onCreate(db);
        }
        if (newVersion >= 12) {
            System.out.println("alter");
            db.execSQL("ALTER TABLE " + ELEMENTOS_TABLE + " ADD COLUMN " + HIDE_FIELD + " INTEGER");
        }
    }
}
