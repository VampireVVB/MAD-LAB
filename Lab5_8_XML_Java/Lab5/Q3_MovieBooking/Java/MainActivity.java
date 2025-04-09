package com.example.lab5q3_moviebookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner movie;
    Spinner theatre;
    ToggleButton prem;
    DatePicker date;
    TimePicker time;
    Button book;
    Button reset;


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

        movie = findViewById(R.id.moviepick);
        theatre = findViewById(R.id.theatrepick);
        prem = findViewById(R.id.tbprem);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        book = findViewById(R.id.bnow);
        reset = findViewById(R.id.reset);

        ArrayList<String> mov = new ArrayList<String>();
        mov.add("Star Wars III");
        mov.add("Prem Ratan Dhan Paayo");
        mov.add("La La Land");
        mov.add("Anora");

        ArrayAdapter<String> adap1 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,mov);

        movie.setAdapter(adap1);

        ArrayList<String> theat = new ArrayList<String>();
        theat.add("Inox@CR2");
        theat.add("PVR@Palladium");
        theat.add("IMAX@Wadala");
        theat.add("Inox@Atria");

        ArrayAdapter<String> adap2 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,theat);

        theatre.setAdapter(adap2);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = date.getDayOfMonth();
                int mon = date.getMonth()+1;
                int year = date.getYear();

                String movdate = day+"/"+mon+"/"+year;

                Toast.makeText(MainActivity.this, "Date: "+movdate, Toast.LENGTH_SHORT).show();

                int hour = time.getHour();
                int min = time.getMinute();

                String movtime = hour+":"+min;
                Toast.makeText(MainActivity.this, "Time: "+movtime, Toast.LENGTH_SHORT).show();

                String movtheatre = theatre.getSelectedItem().toString();
                String m = movie.getSelectedItem().toString();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("movie",m);
                intent.putExtra("theatre", movtheatre);
                intent.putExtra("date", movdate);
                intent.putExtra("time", movtime);

                if(prem.isChecked() && hour<12)
                {
                    Toast.makeText(MainActivity.this, "Premium after 12PM", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    intent.putExtra("prem", prem.getText().toString());
                    startActivity(intent);
                }


            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });

    }
}