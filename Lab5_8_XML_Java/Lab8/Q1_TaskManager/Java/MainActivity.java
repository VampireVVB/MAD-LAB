package com.example.l8q1taskmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    Button ed;
    Button del;
    Button save;
    EditText name;
    EditText due;
    EditText prior;

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

        list = findViewById(R.id.list);
        ed = findViewById(R.id.ed);
        del = findViewById(R.id.del);
        save = findViewById(R.id.save);
        name = findViewById(R.id.name);
        prior = findViewById(R.id.prior);
        due = findViewById(R.id.due);


        ArrayList<String> tasklist = new ArrayList<>();

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayAdapter<String> adap = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, tasklist);

        Cursor cursor = db.query(
                "TaskManager",
                new String[]{"id", "name","due","prior"},
                "",
                new String[]{},
                null, null, null
        );

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String due = cursor.getString(cursor.getColumnIndexOrThrow("due"));
            String prior = cursor.getString(cursor.getColumnIndexOrThrow("prior"));
            tasklist.add(id+"\t\t\t"+name+"\t\t\t"+due+"\t\t\t"+prior);
            //Toast.makeText(MainActivity.this, "Id: "+id+" Name: "+name, Toast.LENGTH_SHORT).show();
        }
        cursor.close();

        list.setAdapter(adap);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String d = due.getText().toString();
                String p = prior.getText().toString();

                ContentValues values = new ContentValues();
                values.put("name", n);
                values.put("due", d);
                values.put("prior", p);
                long newRowId = db.insert("TaskManager", null,values);
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String d = due.getText().toString();
                ContentValues values = new ContentValues();
                values.put("due", d);
                int rowsAffected = db.update("TaskManager", values, "name = ?", new String[]{n});
                Toast.makeText(MainActivity.this, "Rows Affected : "+rowsAffected, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                int rowsDeleted = db.delete("TaskManager","name = ?", new String[]{n});
                Toast.makeText(MainActivity.this, "Rows Deleted: "+rowsDeleted, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MainActivity.class));

            }
        });
    }
}