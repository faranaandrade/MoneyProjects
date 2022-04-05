package com.faaya.moneyprojects.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.faaya.moneyprojects.beans.Calendar;
import com.faaya.moneyprojects.beans.Elementos;
import com.faaya.moneyprojects.beans.FullCalendar;
import com.faaya.moneyprojects.beans.Obras;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;

public class Queries {

    private SQLiteDatabase sqLiteDatabase;

    public Queries(final Context context) {
        DataBase dataBase = new DataBase(context, DataBase.SCHEMA, null, 12);
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

    public List<Obras> getAllObrasSinTerminar() {
        NewItem<Obras> newItem = new NewItem<Obras>() {

            @Override
            public Obras instance(Cursor cursor) {
                return new Obras(cursor);
            }
        };
        return select("SELECT * FROM " + DataBase.OBRAS_TABLE + " WHERE " + DataBase.FINISHED_FIELD + " = ? ", newItem, "0");
    }

    public List<Obras> getAllObrasTerminadas() {
        NewItem<Obras> newItem = new NewItem<Obras>() {

            @Override
            public Obras instance(Cursor cursor) {
                return new Obras(cursor);
            }
        };
        return select("SELECT * FROM " + DataBase.OBRAS_TABLE + " WHERE " + DataBase.FINISHED_FIELD + " = ? ", newItem, "1");
    }

    public List<Obras> getAllObras() {
        NewItem<Obras> newItem = new NewItem<Obras>() {

            @Override
            public Obras instance(Cursor cursor) {
                return new Obras(cursor);
            }
        };
        return select("SELECT * FROM " + DataBase.OBRAS_TABLE + " ORDER BY " + DataBase.FINISHED_FIELD, newItem);
    }

    public List<Elementos> getAllElementos() {
        NewItem<Elementos> newItem = new NewItem<Elementos>() {

            @Override
            public Elementos instance(Cursor cursor) {
                return new Elementos(cursor);
            }
        };
        return select("SELECT * FROM " + DataBase.ELEMENTOS_TABLE + " ORDER BY " + DataBase.TIPO_FIELD, newItem);
    }

    public List<Calendar> getAllCalendarByObra(Long id) {
        NewItem<Calendar> newItem = new NewItem<Calendar>() {

            @Override
            public Calendar instance(Cursor cursor) {
                return new Calendar(cursor);
            }
        };
        return select("SELECT * FROM " + DataBase.CALENDAR_TABLE + " WHERE " + DataBase.OBRA_FIELD + " = ? ORDER BY " + DataBase.ELEMENTO_FIELD, newItem, id.toString());
    }

    public List<Calendar> getAllCalendarByDate(java.util.Calendar calendar) {
        Long purgeTime = calendar.getTimeInMillis();
        System.out.println("filter date:" + new Date(purgeTime));
        NewItem<Calendar> newItem = new NewItem<Calendar>() {

            @Override
            public Calendar instance(Cursor cursor) {
                return new Calendar(cursor);
            }
        };
        return select("SELECT * FROM " + DataBase.CALENDAR_TABLE + " WHERE " + DataBase.FECHA_FIELD + " = ? ", newItem, purgeTime.toString());
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
            saveElementos(item);
        } else {
            updateElementos(item);
        }
    }

    public void saveOrUpdateObras(final Obras item) {
        if (item.getId() == null || item.getId() == 0) {
            saveObras(item);
        } else {
            updateObras(item);
        }
    }

    public void saveOrUpdateCalendar(final Calendar item) {
        if (item.getId() == null || item.getId() == 0) {
            saveCalendar(item);
        } else {
            updateCalendar(item);
        }
    }

    public void saveOrUpdateCalendar(final FullCalendar fullCalendar) {
        Calendar item = fullCalendar.buildCalendar();
        if (item.getId() == null || item.getId() == 0) {
            saveCalendar(item);
            fullCalendar.setId(item.getId());
        } else {
            updateCalendar(item);
        }
    }

    private void updateElementos(final Elementos item) {
        String[] ids = new String[1];
        ids[0] = item.getId().toString();
        sqLiteDatabase.update(DataBase.ELEMENTOS_TABLE, fillElementosContentValues(item), "ID = ?", ids);
    }

    private void updateObras(final Obras item) {
        String[] ids = new String[1];
        ids[0] = item.getId().toString();
        sqLiteDatabase.update(DataBase.OBRAS_TABLE, fillObrasContentValues(item), "ID = ?", ids);
    }

    private void updateCalendar(final Calendar item) {
        String[] ids = new String[1];
        ids[0] = item.getId().toString();
        sqLiteDatabase.update(DataBase.CALENDAR_TABLE, fillCalendarContentValues(item), "ID = ?", ids);
    }

    @NonNull
    private ContentValues fillElementosContentValues(final Elementos item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBase.NAME_FIELD, item.getName());
        contentValues.put(DataBase.COSTO_FIELD, item.getCosto());
        contentValues.put(DataBase.CLASIFICACION_FIELD, item.getClasificacion());
        contentValues.put(DataBase.TIPO_FIELD, item.getTipo());
        contentValues.put(DataBase.HIDE_FIELD, item.getHide());
        return contentValues;
    }

    @NonNull
    private ContentValues fillObrasContentValues(final Obras item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBase.NAME_FIELD, item.getName());
        contentValues.put(DataBase.DOCUMENTO_FIELD, item.getDocumento());
        contentValues.put(DataBase.FINISHED_FIELD, item.getTerminada());
        return contentValues;
    }

    @NonNull
    private ContentValues fillCalendarContentValues(final Calendar item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBase.OBRA_FIELD, item.getObra());
        contentValues.put(DataBase.FECHA_FIELD, item.getDate());
        contentValues.put(DataBase.COSTO_FIELD, item.getCosto());
        contentValues.put(DataBase.ELEMENTO_FIELD, item.getElemento());
        return contentValues;
    }

    private void saveElementos(final Elementos item) {
        if (sqLiteDatabase != null) {
            Long id = sqLiteDatabase.insert(DataBase.ELEMENTOS_TABLE, null, fillElementosContentValues(item));
            item.setId(id);
        }
    }

    private void saveObras(final Obras item) {
        if (sqLiteDatabase != null) {
            Long id = sqLiteDatabase.insert(DataBase.OBRAS_TABLE, null, fillObrasContentValues(item));
            item.setId(id);
        }
    }

    private void saveCalendar(final Calendar item) {
        if (sqLiteDatabase != null) {
            Long id = sqLiteDatabase.insert(DataBase.CALENDAR_TABLE, null, fillCalendarContentValues(item));
            item.setId(id);
        }
    }

    public Elementos getElemento(final Long id) {
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

    public Obras getObra(final Long id) {
        NewItem<Obras> newItem = new NewItem<Obras>() {

            @Override
            public Obras instance(Cursor cursor) {
                return new Obras(cursor);
            }
        };
        List<Obras> items = select("SELECT * FROM " + DataBase.OBRAS_TABLE + " WHERE " + DataBase.ID_FIELD + " = ?", newItem, id.toString());
        if (items.size() == 0) {
            return null;
        }
        return items.get(0);
    }

    public void deleteAllReferenceAndElements(final Integer id) {
        deleteCalendarByIdElement(id);
        deleteElemento(id);
    }

    public void deleteCalendarByIdElement(final Integer id) {
        if (id != null) {
            String[] ids = new String[1];
            ids[0] = id.toString();
            sqLiteDatabase.delete(DataBase.CALENDAR_TABLE, DataBase.ELEMENTO_FIELD + "=?", ids);
        }
    }

    public void deleteElemento(final Integer id) {
        if (id != null) {
            String[] ids = new String[1];
            ids[0] = id.toString();
            sqLiteDatabase.delete(DataBase.ELEMENTOS_TABLE, DataBase.ID_FIELD + "=?", ids);
        }
    }

    public void deleteAllReferenceAndObras(final Integer id) {
        deleteCalendarByIdObra(id);
        deleteObras(id);
    }

    public void deleteCalendarByIdObra(final Integer id) {
        if (id != null) {
            String[] ids = new String[1];
            ids[0] = id.toString();
            sqLiteDatabase.delete(DataBase.CALENDAR_TABLE, DataBase.OBRA_FIELD + "=?", ids);
        }
    }

    public void deleteObras(final Integer id) {
        if (id != null) {
            String[] ids = new String[1];
            ids[0] = id.toString();
            sqLiteDatabase.delete(DataBase.OBRAS_TABLE, DataBase.ID_FIELD + "=?", ids);
        }
    }

    public void deleteCalendar(final Integer id) {
        if (id != null) {
            String[] ids = new String[1];
            ids[0] = id.toString();
            sqLiteDatabase.delete(DataBase.CALENDAR_TABLE, DataBase.ID_FIELD + "=?", ids);
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

    interface NewItem<T> {
        T instance(Cursor cursor);
    }
}