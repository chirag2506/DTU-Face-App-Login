package com.example.dtufaceapplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoggedInActivity extends AppCompatActivity {

    Button startCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        startCam = (Button) findViewById((R.id.startCam));

        startCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                    handlePermission();
                }
            }
        });
    }


    //HANDLING PERMISSION OF CAMERA FOR API > MARSHMALLOW

    private void handlePermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case 1000:
                for(int i = 0; i < permissions.length; i++){
                    String permission = permissions[i];
                    if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                    }
            }
        }
    }
}
