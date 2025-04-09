package com.example.lab8q5sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button bt;
    TextView username;
    TextView logged;
    TextView age;
    Button btget;


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

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        username = findViewById(R.id.username);
        logged = findViewById(R.id.isLoggedIn);
        age = findViewById(R.id.age);
        bt = findViewById(R.id.bt);
        btget = findViewById(R.id.btget);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username","JohnDoe");
                editor.putBoolean("isLoggedIn", true);
                editor.putInt("age", 30);
                editor.apply();
            }
        });

        btget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = sharedPreferences.getString("username", "DefaultUser");
                boolean i = sharedPreferences.getBoolean("isLoggedIn", false);
                int a = sharedPreferences.getInt("age", 0);


                username.setText("Username: "+u);
                logged.setText("LoggedIn: "+i);
                age.setText("Age: "+a);

            }
        });




    }
}