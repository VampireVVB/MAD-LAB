package com.example.grocerylist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner item;
    TextView tot;
    ListView list;
    Button add;
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

        item = findViewById(R.id.itemspin);
        tot = findViewById(R.id.tot);
        list = findViewById(R.id.list);
        add = findViewById(R.id.add);

        ArrayList<String> items = new ArrayList<>();
        items.add("Gobi-40");
        items.add("Potato-20");
        items.add("Tomato-30");
        items.add("Onions-20");

        ArrayAdapter<String> adap = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,items);
        item.setAdapter(adap);

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<String> grocerylist = new ArrayList<>();

        ArrayAdapter<String> adap2 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, grocerylist);

        Cursor cursor = db.query(
                "Grocery",
                new String[]{"id", "name","cost"},
                "",
                new String[]{},
                null, null, null
        );

        int sum = 0;

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int cost = cursor.getInt(cursor.getColumnIndexOrThrow("cost"));
            grocerylist.add(id+"\t\t\t"+name+"\t\t\t"+cost);
            sum = sum+cost;
            //Toast.makeText(MainActivity.this, "Id: "+id+" Name: "+name, Toast.LENGTH_SHORT).show();
        }
        cursor.close();

        list.setAdapter(adap2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s[] = item.getSelectedItem().toString().split("-");
                //int c = Integer.parseInt(s[1]);
                db.execSQL("Insert into Grocery (name, cost) values ('"+s[0]+"', "+(Integer.parseInt(s[1]))+");");
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        tot.setText("Total: "+sum);

    }
}