package com.example.l5q2travelticketbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<String> arrf = new ArrayList<>();
        arrf.add("Mumbai");
        arrf.add("Udupi");
        arrf.add("Bangalore");
        ArrayAdapter<String> adapf = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrf);

        ArrayList<String> arrt = new ArrayList<>();
        arrt.add("Mumbai");
        arrt.add("Udupi");
        arrt.add("Bangalore");
        ArrayAdapter<String> adapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrt);

        Spinner from = findViewById(R.id.fromspin);
        Spinner to = findViewById(R.id.tospin);
        DatePicker dt = findViewById(R.id.date);
        Button sub = findViewById(R.id.sub);
        Button reset = findViewById(R.id. reset);
        ToggleButton tbt = findViewById(R.id.tbt);

        from.setAdapter(adapf);
        to.setAdapter(adapt);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("from",from.getSelectedItem().toString());
                intent.putExtra("to", to.getSelectedItem().toString());

                String date = ""+dt.getDayOfMonth()+"/"+(dt.getMonth()+1)+"/"+dt.getYear();
                intent.putExtra("date",date);
                intent.putExtra("trip",tbt.getText().toString());

                startActivity(intent);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
}