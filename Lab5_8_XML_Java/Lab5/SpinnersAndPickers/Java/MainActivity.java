package com.example.lab5spinnersandpickers;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spin;
    DatePicker datepick;
    Button submit;

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

        ArrayList<String> arr = new ArrayList<>();
        arr.add("Apple");
        arr.add("Banana");
        arr.add("Watermelon");
        arr.add("Muskmelon");
        arr.add("Strawberry");

        spin = findViewById(R.id.itemspinner);
        datepick = findViewById(R.id.datepick);
        submit = findViewById(R.id.bt1);

        ArrayAdapter<String> adap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arr);
        adap.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spin.setAdapter(adap);


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String item = adapterView.getItemAtPosition(pos).toString();
                Toast.makeText(MainActivity.this, "Selected Item : "+item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String day, month, year;
                day = String.valueOf(datepick.getDayOfMonth());
                month = String.valueOf(datepick.getMonth()+1);
                year = String.valueOf(datepick.getYear());

                Toast.makeText(MainActivity.this, "Date : "+day+"/"+month+"/"+year, Toast.LENGTH_SHORT).show();
            }
        });
    }
}