package com.example.l5q2travelticketbook;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    TextView from;
    TextView to;
    TextView date;
    TextView trip;

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
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        date = findViewById(R.id.date);
        trip = findViewById(R.id.trip);


        from.setText("From: "+getIntent().getStringExtra("from"));
        to.setText("To: "+getIntent().getStringExtra("to"));
        date.setText("Date: "+getIntent().getStringExtra("date"));
        trip.setText("Trip : "+getIntent().getStringExtra("trip"));
    }
}