package com.example.askme.sampledata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.askme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class cmnt_item extends AppCompatActivity {
    public static ArrayList<cmnt_item> cmnntList = new ArrayList<>();
    /*******************Load The QuestionList From The FireBase************************/
    public static void loadCmntsFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://askcom-345a0-default-rtdb.firebaseio.com/");
        databaseReference.child("cmnts").orderByChild("question").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cmnt_item.cmnntList.clear();

                for(DataSnapshot cmntSnapshot: snapshot.getChildren()){
                    String question = cmntSnapshot.child("question").getValue(String.class);
                    String cmnt = cmntSnapshot.child("cmnt").getValue(String.class);
                    String username = cmntSnapshot.child("username").getValue(String.class);
                    String date = cmntSnapshot.child("date").getValue(String.class);
                    cmnt_item newCmnt = new cmnt_item(question, username, cmnt, date);
                    cmnt_item.cmnntList.add(newCmnt);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    /*******************Load The QuestionList From The FireBase************************/
    private String question,username,cmnt,date;

    public cmnt_item(String question,String username,String cmnt, String date){
        this.username=username;
        this.question=question;
        this.date = date;
        this.cmnt=cmnt;
    }

    public String getUsername() {
        return username;
    }
    public String getQuestion() {
        return question;
    }
    public String getDate() {
        return date;
    }
    public String getCmnt() {
        return cmnt;
    }

}