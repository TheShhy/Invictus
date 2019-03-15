package com.eszter.lab11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.eszter.lab11.Room.Students;

public class Edit extends AppCompatActivity {
    EditText nameField;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        id = intent.getIntExtra("id" , 0);
        nameField = findViewById(R.id.studentNameEdit);
        nameField.setText(name);
    }

    public void update(View view){
        Students student = new Students(nameField.getText().toString());
        student.setId(id);
        MainActivity.studentsViewModal.update(student);
        finish();
    }

    public void delete(View view){
        Students student = new Students(nameField.getText().toString());
        student.setId(id);
        MainActivity.studentsViewModal.delete(student);
        finish();
    }
}
