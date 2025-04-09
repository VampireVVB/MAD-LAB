package com.example.moviereview;

import android.content.ContentValues;
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

    Button sub;
    Button find;
    EditText name;
    EditText year;
    EditText rate;
    ListView movielist;

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

        movielist = findViewById(R.id.movielist);
        sub = findViewById(R.id.sub);
        find = findViewById(R.id.find);
        name = findViewById(R.id.name);
        year = findViewById(R.id.year);
        rate = findViewById(R.id.rate);


        MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();



        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String y = year.getText().toString();
                int r = Integer.parseInt(rate.getText().toString());

                ContentValues values = new ContentValues();
                values.put("name", n);
                values.put("year", y);
                values.put("rating", r);
                long newRowId = db.insert("Reviews", null,values);
                //Toast.makeText(MainActivity.this, "newRowId: "+newRowId, Toast.LENGTH_SHORT).show();

                //e = e+"1";
                //db.execSQL("INSERT INTO Users (name, email) VALUES ('"+n+"' ,'"+e+"');");
                //Toast.makeText(MainActivity.this, "newRowId: "+newRowId, Toast.LENGTH_SHORT).show();


            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> movlist = new ArrayList<>();

                ArrayAdapter<String> adap = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, movlist);

                String na = name.getText().toString();

                Cursor cursor = db.query(
                        "Reviews",
                        new String[]{"id", "name","year", "rating"},
                        "name = ?",
                        new String[]{na},
                        null, null, null
                );

                while (cursor.moveToNext())
                {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String year = cursor.getString(cursor.getColumnIndexOrThrow("year"));
                    int rating = cursor.getInt(cursor.getColumnIndexOrThrow("rating"));
                    movlist.add(id+"\t\t\t"+name+"\t\t\t"+year+"\t\t\t"+rating);
                    //Toast.makeText(MainActivity.this, "Id: "+id+" Name: "+name, Toast.LENGTH_SHORT).show();
                }
                cursor.close();

                movielist.setAdapter(adap);
            }
        });
    }
}