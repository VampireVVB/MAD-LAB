package com.example.basicdatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button submit;
    Button search;
    Button update;
    Button delete;
    Button create;
    EditText name;
    EditText email;


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

        submit = findViewById(R.id.submit);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        search = findViewById(R.id.search);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        create = findViewById(R.id.create);

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String e = email.getText().toString();

                ContentValues values = new ContentValues();
                values.put("name", n);
                values.put("email", e);
                long newRowId = db.insert("Users", null,values);
                Toast.makeText(MainActivity.this, "newRowId: "+newRowId, Toast.LENGTH_SHORT).show();

                //e = e+"1";
                //db.execSQL("INSERT INTO Users (name, email) VALUES ('"+n+"' ,'"+e+"');");
                //Toast.makeText(MainActivity.this, "newRowId: "+newRowId, Toast.LENGTH_SHORT).show();


            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString();

                /*
                Example File here
                 Cursor cursor = db.query(
                    "Users",                    // Table name\s
                    new String[]{"id", "name"}, // Columns to retrieve\s
                    "email = ?",                // WHERE clause\s
                    new String[]{"john.doe@example.com"}, // Arguments\s
                    null, null, null            // GroupBy, Having, OrderBy\s
                );
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    // Use retrieved data
                }
                cursor.close();
                */

                Cursor cursor = db.query(
                        "Users",
                        new String[]{"id", "name"},
                        "email = ?",
                        new String[]{""+e+""},
                        null, null, null
                );

                while (cursor.moveToNext())
                {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    Toast.makeText(MainActivity.this, "Id: "+id+" Name: "+name, Toast.LENGTH_SHORT).show();
                }

                cursor.close();

                /*
                Basic Syntax here
                Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE email = ?",
                            new String[]{"john.doe@example.com"});
                 */

                cursor = db.rawQuery("SELECT * FROM Users",new String[]{});
                while (cursor.moveToNext())
                {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    Toast.makeText(MainActivity.this, "Id: "+id+" Name: "+name, Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String e = email.getText().toString();
                ContentValues values = new ContentValues();
                values.put("name", n);
                int rowsAffected = db.update("Users", values, "email = ?", new String[]{e});
                Toast.makeText(MainActivity.this, "Rows Affected : "+rowsAffected, Toast.LENGTH_SHORT).show();
                e = e+"1";
                db.execSQL("Update Users set name = '"+n+"' where email = '"+e+"';");
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString();
                int rowsDeleted = db.delete("Users","email = ?", new String[]{e});
                Toast.makeText(MainActivity.this, "Rows Deleted: "+rowsDeleted, Toast.LENGTH_SHORT).show();

                e = e+"1";
                db.execSQL("Delete from Users where email = '"+e+"';");
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("Create Table hello (username TEXT Primary key Unique, password text);");
            }
        });



    }
}