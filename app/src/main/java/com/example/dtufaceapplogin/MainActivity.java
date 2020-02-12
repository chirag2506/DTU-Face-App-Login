package com.example.dtufaceapplogin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.dtufaceapplogin.Model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;


    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;

    EditText editemail, editpassword, editrollno, editphone;
    Button btnsignup;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        //Firebase

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        editemail = (EditText) findViewById(R.id.username);
        editpassword = (EditText) findViewById(R.id.password);
        editrollno = (EditText) findViewById(R.id.rollno);
        editphone = (EditText) findViewById(R.id.phone);

        btnsignup = (Button) findViewById(R.id.login);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User(editemail.getText().toString(), editpassword.getText().toString(), editrollno.getText().toString(), editphone.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getEmail()).exists()) {
                            Toast.makeText(MainActivity.this, "Already Registered", Toast.LENGTH_LONG).show();
                        } else{
                            users.child(user.getEmail()).setValue(user);
                            Toast.makeText(MainActivity.this, "Registered Successfully !", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //NULL
                    }
                });
            }
        });


    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(), "Login");
        adapter.addFragment(new SignUpFragment(), "Sign Up");
        viewPager.setAdapter(adapter);
    }
}