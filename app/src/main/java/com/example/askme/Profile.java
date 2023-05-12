package com.example.askme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private String username;
    protected String email;
    TextView usernameView,emailView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://askcom-345a0-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        usernameView = findViewById(R.id.username);
        emailView = findViewById(R.id.email);
        Intent intent = getIntent();
        username = String.valueOf(intent.getStringExtra("username"));

        databaseReference.child("users").orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email = username ;
                if(snapshot.exists()){
                    for (DataSnapshot userSnapshot: snapshot.getChildren()){
                        email = String.valueOf(userSnapshot.child("email").getValue(String.class));
                        emailView.setText(email);
                    }
                } else {
                    email = "THERE IS AN ERROR GETTING THE MAIL :(";
                    emailView.setText(email);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        usernameView.setText(username);


    }

    //*****************************Back***************************************//
    public void back(View view) {
        finish();
    }
    //*****************************/Back**************************************//

    //*****************************newQuestion***************************************//
    public void newQuestion(View view){
        Intent newQuestionIntent = new Intent(Profile.this,AddQuestion.class);
        startActivity(newQuestionIntent);
    }
    //*****************************/newQuestion**************************************//
}