package com.example.dtufaceapplogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dtufaceapplogin.Model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";

    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;

    EditText editusername, editpassword;
    Button btnLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        //Firebase

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        editusername = view.findViewById(R.id.loginusername);
        editpassword = view.findViewById(R.id.loginpassword);

        btnLogin = (Button) view.findViewById(R.id.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin(editusername.getText().toString(),
                        editpassword.getText().toString());

            }
        });

        return view;
    }

    private void signin(final String username, final String password) {

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    User login = dataSnapshot.child(username).getValue(User.class);
                    if(login.getPassword().equals(password)){
                        Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_LONG).show();
                        openLoggedInActivity();
                    } else{
                        Toast.makeText(getActivity(), "Incorrect Password", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Not Registered", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void openLoggedInActivity(){
        Intent s = new Intent(getActivity(), LoggedInActivity.class);
        startActivity(s);
    }
}
