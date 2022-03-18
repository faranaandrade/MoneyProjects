package com.faaya.moneyprojects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.faaya.moneyprojects.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
    }

    public void buttonHoyAction(View v) {
        Intent intent = new Intent(this, HoyActivity.class);
        startActivity(intent);
    }

    public void buttonCalendarioAction(View v) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void buttonObrasAction(View v) {
        Intent intent = new Intent(this, ObrasActivity.class);
        startActivity(intent);
    }

    public void buttonElementosAction(View v) {
        Intent intent = new Intent(this, ElementosActivity.class);
        startActivity(intent);
    }

}