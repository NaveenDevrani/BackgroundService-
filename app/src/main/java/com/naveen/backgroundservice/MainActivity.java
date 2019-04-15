package com.naveen.backgroundservice;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.naveen.backgroundservice.services.BackgroundService;

public class MainActivity extends AppCompatActivity {

    Button btn_start_service, btn_stop_service;
    Intent mServiceIntent;
    private BackgroundService mYourService;
    private String Tag="MainActivity()-->";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_stop_service=findViewById(R.id.btn_stop_service);
        btn_start_service=findViewById(R.id.btn_start_service);

        mYourService = new BackgroundService();
        mServiceIntent = new Intent(this, mYourService.getClass());

        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!isMyServiceRunning(mYourService.getClass())) {
                    startService(mServiceIntent);
//                }
            }
        });
        btn_stop_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(mServiceIntent);
            }
        });
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i ("Service status", "Running");
                return true;
            }
        }
        Log.i ("Service status", "Not running");
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (isMyServiceRunning(mYourService.getClass()))
         stopService(mServiceIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(Tag,"onDestory");
//        if (!isMyServiceRunning(mYourService.getClass()))
        startService(mServiceIntent);
    }
}
