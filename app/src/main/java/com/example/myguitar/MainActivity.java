package com.example.myguitar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.black));
        setContentView(R.layout.activity_main);
        page = findViewById(R.id.page);
    }
    public void goToPage(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

}