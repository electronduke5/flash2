package com.example.flash2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button _btnOn;

    private boolean _lightIsOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _btnOn = findViewById(R.id.btnLight);
        _btnOn.setOnClickListener(view -> btnOnClicked());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void btnOnClicked()
    {
        _lightIsOn = !_lightIsOn;
        if(_lightIsOn)
        {
            try {
                turnOn();
            }
            catch (Exception e)
            {

            }
        }
        else
        {
            try {
                turnOff();
            }
            catch (Exception e)
            {

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void turnOn() throws CameraAccessException {
        CameraManager manager =
                (CameraManager) getApplicationContext().getSystemService(getApplicationContext().CAMERA_SERVICE);
        String cameraId = manager.getCameraIdList()[0];

        manager.setTorchMode(cameraId, true);
        _lightIsOn = true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void turnOff() throws CameraAccessException {
        CameraManager manager =
                (CameraManager) getApplicationContext().getSystemService(getApplicationContext().CAMERA_SERVICE);
        String cameraId = manager.getCameraIdList()[0];

        manager.setTorchMode(cameraId, false);
        _lightIsOn = false;
    }
}