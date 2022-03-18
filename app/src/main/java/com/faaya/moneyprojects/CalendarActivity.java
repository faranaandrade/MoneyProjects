package com.faaya.moneyprojects;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import com.faaya.moneyprojects.beans.Calendar;
import com.faaya.moneyprojects.beans.FullCalendar;
import com.faaya.moneyprojects.database.Queries;
import com.faaya.moneyprojects.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarActivity extends AppCompatActivity {

    public static final String FULL_CALENDAR = "FullCalendar";
    public static final String SHOW = "SHOW";
    public static final String OBRAS = "OBRAS";
    public static final String ELEMENTOS = "ELEMENTOS";
    public static final String DATE = "DATE";
    private List<FullCalendar> allCalendars;
    private CalendarAdapter calendarAdapter;
    private java.util.Calendar selectedCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);

        selectedCalendar = Utils.getPurgeCalendar();

        CalendarView calendarView = findViewById(R.id.calendarView);

        Bundle b = getIntent().getExtras();
        if (b != null && b.getSerializable(DATE) != null) {
            selectedCalendar = (java.util.Calendar) b.getSerializable(DATE);
            calendarView.setDate(selectedCalendar.getTimeInMillis());
        }

        final Queries queries = new Queries(this);


        RecyclerView listViewCalendar = findViewById(R.id.calendarRecyclerView);
        allCalendars = convert(queries, queries.getAllCalendarByDate(selectedCalendar));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int mounth, int day) {
                selectedCalendar.set(year, mounth, day);
                calendarView.setDate(selectedCalendar.getTimeInMillis());
                allCalendars.clear();
                allCalendars.addAll(convert(queries, queries.getAllCalendarByDate(selectedCalendar)));
                calendarAdapter.notifyDataSetChanged();
            }
        });

        ShowEdit<FullCalendar> showEdit = new ShowEdit<FullCalendar>() {
            @Override
            public void show(FullCalendar calendarEdit) {
                CalendarManager manager = new CalendarManager() {
                    @Override
                    public void add(FullCalendar fullCalendar) {
                        if (calendarEdit.getId() == null) {
                            allCalendars.add(fullCalendar);
                        }
                        queries.saveOrUpdateCalendar(fullCalendar);
                        calendarAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void delete(Long id) {
                        queries.deleteCalendar(id.intValue());
                        allCalendars.remove(calendarEdit);
                        calendarAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void goObras(FullCalendar fullCalendar) {
                        Intent intent = new Intent(CalendarActivity.this, ListActivity.class);
                        intent.putExtra(OBRAS, true);
                        intent.putExtra(FULL_CALENDAR, fullCalendar);
                        intent.putExtra(DATE, selectedCalendar);
                        startActivity(intent);
                    }

                    @Override
                    public void goElementos(FullCalendar fullCalendar) {
                        Intent intent = new Intent(CalendarActivity.this, ListActivity.class);
                        intent.putExtra(ELEMENTOS, true);
                        intent.putExtra(FULL_CALENDAR, fullCalendar);
                        intent.putExtra(DATE, selectedCalendar);
                        startActivity(intent);
                    }
                };
                AddCalendarFragment.newInstance("", calendarEdit, manager, selectedCalendar.getTimeInMillis()).show(getSupportFragmentManager(), "fragment_edit_calendar");
            }
        };

        FloatingActionButton floatingActionButton = findViewById(R.id.fabCalendar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEdit.show(null);
            }
        });

        calendarAdapter = new CalendarAdapter(allCalendars, showEdit);
        listViewCalendar.setAdapter(calendarAdapter);
        listViewCalendar.setLayoutManager(new LinearLayoutManager(this));


        boolean show = false; // or other values
        if (b != null)
            show = b.getBoolean(SHOW, false);

        if (show) {
            showEdit.show((FullCalendar) b.getSerializable(FULL_CALENDAR));
        }
    }

    private List<FullCalendar> convert(Queries queries, List<Calendar> allCalendarByDate) {
        List<FullCalendar> fullCalendars = new ArrayList<>();
        for (Calendar calendar : allCalendarByDate) {
            fullCalendars.add(new FullCalendar(queries, calendar));
        }
        return fullCalendars;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                comeBack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void comeBack() {
        Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            comeBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}