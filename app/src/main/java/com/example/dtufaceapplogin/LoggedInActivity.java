package com.example.dtufaceapplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                Intent s = new Intent(this, ClickPictures.class);
                startActivity(s);
            }
        });
    }
}
