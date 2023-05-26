package com.example.askme;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.askme.sampledata.Question_item;
import com.example.askme.sampledata.cmnt_item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private String username;
    protected String email,createdDate;
    TextView usernameView,emailView,createdDateView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://askcom-345a0-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        usernameView = findViewById(R.id.username);
        emailView = findViewById(R.id.email);
        createdDateView = findViewById(R.id.joinDate);

        username = data.getInstance().getUsername();
        //*********************Problem Of FireBase Delay*************************//
        Question_item.loadQuestionsFromFirebase();
        cmnt_item.loadCmntsFromFirebase();
        //*********************/Problem Of FireBase Delay************************//

        databaseReference.child("users").orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email = username ;
                if(snapshot.exists()){
                    for (DataSnapshot userSnapshot: snapshot.getChildren()){
                        email = String.valueOf(userSnapshot.child("email").getValue(String.class));
                        createdDate = String.valueOf(userSnapshot.child("createdDate").getValue(String.class));
                        emailView.setText(email);
                        createdDateView.setText(createdDate);
                    }
                } else {
                    email = "azizzouaghia@gmail.com";
                    createdDate = "27/05/2023";
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
    //*****************************EditProfile***************************************//
    public void editProfil(View v){
        Intent intent = new Intent(Profile.this,editUser.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("createdDate",createdDate);
        startActivity(intent);
        finish();
    }
    //****************************/EditProfile***************************************//

}