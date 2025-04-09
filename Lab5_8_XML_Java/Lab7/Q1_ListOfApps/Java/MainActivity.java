package com.example.l7q1listofapps;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView appL;
    String app;
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

        ArrayList<String> applist = new ArrayList<>();
        applist.add("WhatsApp");
        applist.add("Gmail");
        applist.add("Camera");
        applist.add("Files");
        ArrayAdapter<String> adap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,applist);

        appL = findViewById(R.id.list);
        registerForContextMenu(appL);
        appL.setAdapter(adap);
        appL.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        appL.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                if(i == 0)
                    app = "WhatsApp";
                else if(i == 1)
                    app = "Gmail";
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater().inflate(R.menu.detail_menu,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                int x = menuItem.getItemId();
                if(x == R.id.open)
                {
                    Toast.makeText(MainActivity.this, "Opening App: "+app, Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    return true;
                }
                else if(x == R.id.det)
                {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("AppName", app);
                    startActivity(intent);
                    actionMode.finish();
                    return true;
                }
                else if(x == R.id.unist)
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                    alert.setTitle("Are You Sure");
                    alert.setMessage("Are you sure you want to delete the app");


                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Toast.makeText(MainActivity.this, "Uninstalled", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alert.show();
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

    }
}