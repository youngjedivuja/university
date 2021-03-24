package com.example.cs330_dz02_3860_jovanvujovic;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Student1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener((view)->{
            OpenUrlDialog dialog = new OpenUrlDialog();
            dialog.show(getSupportFragmentManager(), "OpenUrlDialog");
        });
    }
}