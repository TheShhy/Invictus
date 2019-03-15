package com.eszter.lab_12;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    final int PERMISSION_REQUEST_CODE = 21;
    final String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
    private TextView locationText;
    private FusedLocationProviderClient flpc;
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationText = findViewById(R.id.location);
    }

    void initialize(View view){
        if (!hasPermission(permissions)){
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
        }
        else {
            getLocation();
        }
    }

    void getLocation(){
        try {
            flpc = LocationServices.getFusedLocationProviderClient(this);
            flpc.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        geocoder(location.getLatitude(), location.getLongitude());
                    } else {
                        Toast.makeText(MainActivity.this, "No Location Found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            flpc.requestLocationUpdates(getLocationRequest(), mLocationCallback, null);
        }catch (SecurityException e){
            e.printStackTrace();
        }

    }

    void geocoder(double lattitude, double longitude){
        try {
            geocoder = new Geocoder(MainActivity.this , Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(lattitude, longitude , 1);
            locationText.setText(addresses.get(0).getAddressLine(0));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000); //for ten seconds
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

   LocationCallback mLocationCallback = new LocationCallback() {
       @Override
       public void onLocationResult(LocationResult locationResult) {
           for (Location location : locationResult.getLocations()) {
               // Update UI with location data
               // ...
               geocoder(location.getLatitude(), location.getLongitude());
           }
       }
   };

    boolean hasPermission(String ... permissions){
        for (String permission : permissions)
            return ActivityCompat.checkSelfPermission(this , permission) == PackageManager.PERMISSION_GRANTED;
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults.length > 0){
            Toast.makeText(this, "All Required permissions are granted", Toast.LENGTH_SHORT).show();
            getLocation();
        }
        else {
            Toast.makeText(this, "Please grant required permissions", Toast.LENGTH_SHORT).show();
        }
    }
}
