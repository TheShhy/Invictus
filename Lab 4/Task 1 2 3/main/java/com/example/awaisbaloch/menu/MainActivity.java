package com.example.awaisbaloch.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();
    int count =0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Toast.makeText(getBaseContext(), item+"Clicked",Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.one:
                // do something
                return true;

            case R.id.two:
                //do something
                return true;
            case R.id.three:
                //do something
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listview);

        list.add("Bilal");
        list.add("AHad");
        list.add("Awais");
        list.add("ali");
        list.add("sahil");
        list.add("dhengra");
        list.add("machar");
        list.add("nookeela");
        list.add("wahaj");
        list.add("mcstan");
        list.add("jadu");
        list.add("emiway");
        list.add("khatam");
        list.add("samjh");
        list.add("sheikh chilli");
        list.add("doodhwala");
        list.add("mithaiwala");
        list.add("andaywalaburger");
        list.add("pakistan");
        list.add("umreeka");
        list.add("peshawar");
        list.add("Washamallay");
        list.add("bilal");
        list.add("Sahil");
        list.add("Awais");
        list.add("ahad");
        list.add("ali");
        adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1,list);
        listview.setAdapter(adapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                count = count+1;
                mode.setTitle(count+"items selected");
                list.add(list.get(position));


            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.main_menu,menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.delete_id:
                        for (String msg : list){
                            adapter.remove(msg);
                        }
                        Toast.makeText(getBaseContext(), count+"Items Removed",Toast.LENGTH_SHORT).show();
                        count +=1;
                        mode.finish();
                        return true;
                        //break;
                        default:
                            return false;

                }
                //return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });

  //  Create Adapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

//assign adapter to listview

        listview.setAdapter(arrayAdapter);

//add listener to listview
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "clicked item:" + i + " " + list.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}