package com.example.admin.lab_8;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    SMSBroadcastReceiver smsReceiver;
    private final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.READ_SMS
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Check for permissions
        if(!hasRequiredPermissions(REQUIRED_PERMISSIONS)){
            ActivityCompat.requestPermissions(this , REQUIRED_PERMISSIONS , 10);
        }
        //Register Broadcast Receiver
        smsReceiver = new SMSBroadcastReceiver();
        registerReceiver(smsReceiver, new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION));
        smsReceiver.setListener(new SMSBroadcastReceiver.Listener() {
            @Override
            public void onSmsReceived(String sms) {
                if (sms.startsWith("Hello")){
                    Toast.makeText(MainActivity.this , "Message Received" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsReceiver);
    }
    //----------------------------------------------------------------------------------------------------------------
    /**
     *  Method for checkig required permissions
     * @param permissions   all required permissons
     * @return
     */
    private boolean hasRequiredPermissions(String ... permissions){
        for (String permission : permissions )  {
            return ActivityCompat.checkSelfPermission(this , permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (permissions[0].equalsIgnoreCase(Manifest.permission.READ_SMS)) {
            //Permission Are Granted
            Toast.makeText(this, "Read SMS permission granted", Toast.LENGTH_SHORT).show();
            //Now Send The Message
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    SmsManager.getDefault().sendTextMessage("03483318738" , null, "Hello Shahzaib" , null , null);
                }
            };
            timer.schedule(timerTask , 5000);

        } else {
            //Permission Are Not Granted
            Toast.makeText(this, "Read SMS permission denied", Toast.LENGTH_SHORT).show();
        }
    }
}
