package com.example.dtufaceapplogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class LoggedInActivity extends AppCompatActivity {

    Button startCam;
    private static final int CAMERA_REQUEST_CODE = 1;
    private StorageReference mStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        startCam = (Button) findViewById((R.id.startCam));
//        mStorage = FirebaseStorage.getInstance().getReference();


        startCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dispatchTakeVideoIntent();
//
//                //HANDLE PERMISSION
//                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
//                    handlePermission();
//                }
//                //PERMISSION HANDLED
//
//                Intent s = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                startActivityForResult(s, 0);


            }
        });
    }

    static final int REQUEST_VIDEO_CAPTURE = 1;

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
//
//            Uri uri = data.getData();
//            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());
//
//            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    Toast.makeText(LoggedInActivity.this, "Upload Finish", Toast.LENGTH_LONG).show();
//
//                }
//            });
//
//        }
//
//    }
//
//    //HANDLING PERMISSION OF CAMERA FOR API > MARSHMALLOW
//
//    private void handlePermission() {
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1000);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        switch (requestCode){
//            case 1000:
//                for(int i = 0; i < permissions.length; i++){
//                    String permission = permissions[i];
//                    if(grantResults[i] == PackageManager.PERMISSION_DENIED){
//                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
//                    }
//            }
//        }
//    }
}
