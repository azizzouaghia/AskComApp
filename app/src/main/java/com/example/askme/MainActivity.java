package com.example.askme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askme.sampledata.Question_item;
import com.example.askme.sampledata.cmnt_item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://askcom-345a0-default-rtdb.firebaseio.com/");
    EditText usernameEditText,passEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView createAccount = findViewById(R.id.createAccountTextView);
        Button loginButton = findViewById(R.id.loginButton);
        usernameEditText = findViewById(R.id.usernameEditText);
        passEditText = findViewById(R.id.passwordEditText);

        //*********************Problem Of FireBase Delay*************************//
        /**//**//**//**/Question_item.loadQuestionsFromFirebase();//**//**//**//**/
                            cmnt_item.loadCmntsFromFirebase();
        //*********************/Problem Of FireBase Delay************************//



        //*********************Login*************************//
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username,pass;
                username = String.valueOf(usernameEditText.getText());
                pass = String.valueOf(passEditText.getText());

                if (TextUtils.isEmpty(username)){ //Check if username is empty
                    Toast.makeText(MainActivity.this, "The username field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)){ //Check if pass is empty
                    Toast.makeText(MainActivity
                            .this, "The password field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseReference.child("users").orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Username exists, now compare the passwords
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                String getPass = userSnapshot.child("password").getValue(String.class);
                                if (getPass.equals(pass)) {
                                    // Password is correct
                                    Toast.makeText(MainActivity.this,"Welcome "+username,Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                                    intent.putExtra("username",username);
                                    data.getInstance().setUsername(username);
                                    startActivity(intent);
                                    finish();
                                    return;
                                }
                            }
                            // Password is incorrect
                            Toast.makeText(MainActivity.this,"Password is incorrect",Toast.LENGTH_LONG).show();
                        } else {
                            // Username doesn't exist
                            Toast.makeText(MainActivity.this,"Username is incorrect",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle errors here
                    }
                });


            }
        });


        //********************/Login*************************//


        //***********************RegisterPage Redirection**********************//
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegsiterActivity.class);
                startActivity(intent);
            }
        });
        //**********************/RegisterPage Redirection**********************//

    }

    public void about(View v){
        Intent intent = new Intent(MainActivity.this, about.class);
        startActivity(intent);
    }
}