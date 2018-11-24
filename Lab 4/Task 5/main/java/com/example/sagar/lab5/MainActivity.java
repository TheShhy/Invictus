package com.example.sagar.lab5;

import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> listData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        setupList();
        RecyclerView list = findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter adapter = new RecyclerAdapter(listData,this);
        list.setAdapter(adapter);
        Toast.makeText(this,"Scroll Up",Toast.LENGTH_SHORT).show();

    }

    private void setupList() {
        for (int i=0; i<=10; i++)
            listData.add(i+" Element");
    }
}

