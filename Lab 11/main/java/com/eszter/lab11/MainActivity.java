package com.eszter.lab11;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.eszter.lab11.Room.Students;
import com.eszter.lab11.Room.StudentsViewModal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    public static StudentsViewModal studentsViewModal;
    FloatingActionButton addBt;
    List<Students> allList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        addBt = findViewById(R.id.addBtn);

        monitorDatabaseChanger();
    }

    private void monitorDatabaseChanger(){
        studentsViewModal = ViewModelProviders.of(this).get(StudentsViewModal.class);
        studentsViewModal.getAllStudents().observe(this, new Observer<List<Students>>() {
            @Override
            public void onChanged(@Nullable List<Students> list) {
                allList = list;
                List<String> names = new ArrayList<>();
                for(Students s : list)
                    names.add(s.getName());

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this ,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1 , names);
                listView.setAdapter(arrayAdapter);
                manageListView();
            }
        });
    }

    void insertStudent(View view){
        startActivityForResult(new Intent(this, Insert.class) , 21);
    }

    void manageListView(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, allList.get(i).getName(), Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(MainActivity.this , Edit.class)
                        .putExtra("id" , allList.get(i).getId())
                        .putExtra("name" , allList.get(i).getName()) , 22);
            }
        });
    }
}
