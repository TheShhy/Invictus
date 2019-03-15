package com.example.admin.lab_7;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private final int REQUIRED_PERMISSIONS_CODE = 10;
    private final String[] PERMISSIONS = {
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN
    };
    private Switch bluetooth_switch;
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> bluetoothDevices;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!hasPermission(PERMISSIONS)){
            ActivityCompat.requestPermissions(this , PERMISSIONS , REQUIRED_PERMISSIONS_CODE);
        }
        bluetooth_switch = findViewById(R.id.bluetooth_switch);
        listView = findViewById(R.id.listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.discoverable){
            bluetoothAdapter.startDiscovery();
            Toast.makeText(this, "Bluetooth is now discoverable" , Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private boolean hasPermission(String ... permissions){
        for(String permission : permissions){
            return ActivityCompat.checkSelfPermission(this , permission) != PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions[0].equalsIgnoreCase(PERMISSIONS[0]) && permissions[1].equalsIgnoreCase(PERMISSIONS[1])){
            Toast.makeText(this , "All permissions are granted", Toast.LENGTH_SHORT).show();
            initialize();
        }
        else {
            Toast.makeText(this , "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            Toast.makeText(this , "Bluetooth is enabled" , Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize(){

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothDevices = bluetoothAdapter.getBondedDevices();
        List<String> devicesNames = new ArrayList<String>();
        for (BluetoothDevice bd : bluetoothDevices){
            devicesNames.add(bd.getName());
        }
        if (devicesNames.size() <= 0)
            devicesNames.add("No Device Paired");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , devicesNames);
        listView.setAdapter(adapter);

        bluetooth_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE) , 1);
                }
                else {
                    bluetoothAdapter.disable();
                    Toast.makeText(MainActivity.this , "Bluetooth is disabled" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
