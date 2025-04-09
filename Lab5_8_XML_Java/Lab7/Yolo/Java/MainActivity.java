package com.example.yolo;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button bt;

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
        bt = findViewById(R.id.button);
        registerForContextMenu(bt);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {

        int x = item.getItemId();
        if(x == R.id.delg)
        {
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (x == R.id.joing)
        {
            Toast.makeText(this, "Join", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(x == R.id.newg)
        {
            Toast.makeText(this, "New", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
            return super.onContextItemSelected(item);
    }
}