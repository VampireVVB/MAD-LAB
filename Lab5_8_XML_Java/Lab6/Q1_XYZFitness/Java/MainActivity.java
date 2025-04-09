package com.example.l6qxyzfitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int x = item.getItemId();
        if(x == R.id.plans)
        {
            startActivity(new Intent(MainActivity.this, WorkOutPlans.class));
            return true;
        }
        else if(x == R.id.train)
        {
            startActivity(new Intent(MainActivity.this, Trainers.class));
            return true;
        }
        else if(x == R.id.member)
        {
            startActivity(new Intent(MainActivity.this, Membership.class));
            return true;
        }
        else if(x == R.id.about)
        {
            Toast.makeText(this, "We are XYZFitness", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(x == R.id.home)
        {
            Toast.makeText(this, "Going to Homepage", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (x == R.id.con)
        {
            Toast.makeText(this, "Phone: 7977747959", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}