package com.example.lab5q3_moviebookingsystem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    TextView mov;
    TextView theat;
    TextView d;
    TextView t;
    TextView p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mov = findViewById(R.id.mov);
        theat = findViewById(R.id.theat);
        d = findViewById(R.id.date);
        t = findViewById(R.id.time);
        p = findViewById(R.id.prem);

        String movie = getIntent().getStringExtra("movie");
        String theatre = getIntent().getStringExtra("theatre");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String prem = getIntent().getStringExtra("prem");

        mov.setText("Movie: "+movie);
        theat.setText("Theatre: "+theatre);
        d.setText("Date: "+date);
        t.setText("Time: "+time);
        p.setText("Premium: "+prem);
    }
}