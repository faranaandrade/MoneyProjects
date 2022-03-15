package com.example.moneyprojects.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.moneyprojects.beans.Elementos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;

public class Queries {

    private SQLiteDatabase sqLiteDatabase;

    public Queries(final Context context) {
        DataBase dataBase = new DataBase(context, DataBase.SCHEMA, null, 5);
        sqLiteDatabase = dataBase.getWritableDatabase();
    }

    public List<Elementos> getAllGruas() {
        NewItem<Elementos> newItem = new NewItem<Elementos>() {

            @Override
            public Elementos instance(Cursor cursor) {
                return new Elementos(cursor);
            }
        };
        return select("SELECT * FROM " + DataBase.ELEMENTOS_TABLE + " WHERE " + DataBase.TIPO_FIELD + " = ? ", newItem, DataBase.GRUA_TIPO);
    }

    public List<Elementos> getAllCuadrillas() {
        NewItem<Elementos> newItem = new NewItem<Elementos>() {

            @Override
            public Elementos instance(Cursor cursor) {
                return new Elementos(cursor);
            }
        };
        return select("SELECT * FROM " + DataBase.ELEMENTOS_TABLE + " WHERE " + DataBase.TIPO_FIELD + " = ? ", newItem, DataBase.CUADRILLA_TIPO);
    }

    @NonNull
    private <T> List<T> select(final String sql, NewItem<T> newItem, final String... args) {
        Cursor cursor = sqLiteDatabase.rawQuery(sql, args);
        final List<T> items = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                items.add(newItem.instance(cursor));
            } while (cursor.moveToNext());
        }
        return items;
    }

    public void saveOrUpdateElementos(final Elementos item) {
        if (item.getId() == null || item.getId() == 0) {
            if(item.getName() == null){
               item.setName("E-" + UUID.randomUUID().toString());
            }
            saveElementos(item);
        } else {
            updateElementos(item);
        }
    }

    private void updateElementos(final Elementos item) {
        String[] ids = new String[1];
        ids[0] = item.getId().toString();
        sqLiteDatabase.update(DataBase.ELEMENTOS_TABLE, fillElementosContentValues(item), "ID = ?", ids);
    }

    @NonNull
    private ContentValues fillElementosContentValues(final Elementos item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBase.NAME_FIELD, item.getName());
        contentValues.put(DataBase.COSTO_FIELD, item.getCosto());
        contentValues.put(DataBase.CLASIFICACION_FIELD, item.getClasificacion());
        contentValues.put(DataBase.TIPO_FIELD, item.getTipo());
        return contentValues;
    }

    private void saveElementos(final Elementos item) {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.insert(DataBase.ELEMENTOS_TABLE, null, fillElementosContentValues(item));
        }
    }

    public Elementos getElemento(final Integer id) {
        NewItem<Elementos> newItem = new NewItem<Elementos>() {

            @Override
            public Elementos instance(Cursor cursor) {
                return new Elementos(cursor);
            }
        };
        List<Elementos> items = select("SELECT * FROM " + DataBase.ELEMENTOS_TABLE + " WHERE " + DataBase.ID_FIELD + " = ?", newItem, id.toString());
        if (items.size() == 0) {
            return null;
        }
        return items.get(0);
    }

    public void deleteElemento(final Integer id) {
        if (id != null) {
            String[] ids = new String[1];
            ids[0] = id.toString();
            sqLiteDatabase.delete(DataBase.ELEMENTOS_TABLE, DataBase.NAME_FIELD + "=?", ids);
        }
    }

    public long getCountElementos() {
        return getCount("SELECT COUNT(1) FROM " + DataBase.ELEMENTOS_TABLE);
    }

    public long getCount(String sql) {
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[0]);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return 0;
    }

     interface NewItem <T> {
        T instance(Cursor cursor);
    }
}