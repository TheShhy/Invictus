package com.geeks.asmat.berryberry;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private ListView listView;
    private List<String> list;
    private String listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        listView = findViewById(R.id.listView);
        registerForContextMenu(listView);
        getSupportActionBar().setLogo(R.drawable.ic_search);
        list  = new ArrayList<>();
        for(int i=1; i<=100; i+=2)
        {
            list.add("F16SW"+i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = listView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"User Clicked "+s, Toast.LENGTH_LONG).show();

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listItem = listView.getItemAtPosition(position).toString();
                openContextMenu( listView );
                return true;
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        //find out which menu item was pressed
        switch (item.getItemId()) {
            case R.id.action_delete:
                onClickShowAlert(listView);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh:
                Toast.makeText(getApplicationContext(), "List Refreshed", Toast.LENGTH_LONG).show();
                refreshList();
                return true;
            case R.id.item1:
                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(), "Item 2 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(), "Item 3 Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickShowAlert(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
        // Set the dialog title and message.
        myAlertBuilder.setTitle("Alert");
        myAlertBuilder.setMessage("Are you sure you want to delete this item?\nClick OK to continue, or Cancel to stop:");
        // Add the dialog buttons.
        myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User clicked OK button.
                deleteListItem();
                Toast.makeText(getApplicationContext(), listItem+" Removed", Toast.LENGTH_SHORT).show();
            }
        });
        myAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User cancelled the dialog.
                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
            }
        });
        // Create and show the AlertDialog.
        myAlertBuilder.show();
    }
    private void deleteListItem()
    {
        list.remove(listItem);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(arrayAdapter1);
    }
    private void refreshList()
    {
        list  = new ArrayList<>();
        for(int i=1; i<=100; i+=2)
        {
            list.add("F16SW"+i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(arrayAdapter);
    }
}
