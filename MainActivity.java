package com.example.myapplication;

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

    DatabaseHelper myDb;   // Create an instance of DatabaseHelper class

    EditText editName, editSurname, editMarks, editSearch;
    Button btnAddData, btnUpdateData, btnDeleteData;

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

        myDb = new DatabaseHelper(this);    // Call the constructor of the DatabaseHelper class.
        editName = findViewById(R.id.editTextText);
        editSurname = findViewById(R.id.editTextText2);
        editMarks = findViewById(R.id.editTextText3);
        btnAddData = findViewById(R.id.button);
        btnUpdateData = findViewById(R.id.button2);
        btnDeleteData = findViewById(R.id.button3);
        editSearch = findViewById(R.id.editTextSearch);

        AddData();
        UpdateData();
        DeleteData();
    }

    // Method to add data to the database
    public void AddData() {
        btnAddData.setOnClickListener(v -> {
            String name = editName.getText().toString();
            String surname = editSurname.getText().toString();
            String marks = editMarks.getText().toString();

            if (name.isEmpty() || surname.isEmpty() || marks.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();
            } else {
                boolean isInserted = myDb.insertData(name, surname, marks);

                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Method to update data in the database
    public void UpdateData() {
        btnUpdateData.setOnClickListener(v -> {
            // Get the Name or Surname to search for the record
            String searchQuery = editSearch.getText().toString();
            String name = editName.getText().toString();
            String surname = editSurname.getText().toString();
            String marks = editMarks.getText().toString();

            if (searchQuery.isEmpty() || name.isEmpty() || surname.isEmpty() || marks.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();
            } else {
                // Find the ID based on Name or Surname
                String studentId = myDb.getStudentIdByNameOrSurname(searchQuery);

                if (studentId != null) {
                    // Update the data in the database
                    boolean isUpdated = myDb.updateData(studentId, name, surname, marks);

                    if (isUpdated) {
                        Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Record not found", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteData() {
        btnDeleteData.setOnClickListener(v -> {
            // Get the Name to search for the record
            String searchQuery = editSearch.getText().toString();

            if (searchQuery.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter Name to search", Toast.LENGTH_LONG).show();
            } else {
                // Delete the data by Name
                boolean isDeleted = myDb.deleteDataByName(searchQuery);

                if (isDeleted) {
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Record not found", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
