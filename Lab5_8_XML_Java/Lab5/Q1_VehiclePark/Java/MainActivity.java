package com.example.l5q1vehicleparkingregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner type;
    Button subbt;
    EditText vno;
    EditText rcno;

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
        arr.add("Car");
        arr.add("Bike");
        arr.add("Truck");

        ArrayAdapter<String> adap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arr);


        type = findViewById(R.id.typespin);
        subbt = findViewById(R.id.subbt);
        vno = findViewById(R.id.vno);
        rcno = findViewById(R.id.rcno);

        type.setAdapter(adap);

        subbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("vno", vno.getText().toString());
                intent.putExtra("type", type.getSelectedItem().toString());
                intent.putExtra("rcno", rcno.getText().toString());
                startActivity(intent);
            }
        });
    }
}