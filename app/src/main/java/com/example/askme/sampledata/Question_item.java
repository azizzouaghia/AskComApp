package com.example.askme.sampledata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.askme.DashboardActivity;
import com.example.askme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;


public class Question_item extends AppCompatActivity {
    public static ArrayList<Question_item> questionList = new ArrayList<>();
    /*******************Load The QuestionList From The FireBase************************/
    public static void loadQuestionsFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://askcom-345a0-default-rtdb.firebaseio.com/");
        databaseReference.child("question").orderByChild("text").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                questionList.clear();
                for(DataSnapshot questionSnapshot: snapshot.getChildren()){
                    String question = questionSnapshot.child("text").getValue(String.class);
                    String username = questionSnapshot.child("username").getValue(String.class);
                    String category = questionSnapshot.child("category").getValue(String.class);
                    Question_item newQuestion = new Question_item(username, question, new Date(), category);
                    questionList.add(newQuestion);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    /*******************Load The QuestionList From The FireBase************************/
    private int id;
    private String username;
    private int UserId;
    private String QuestionText;
    private int CatId;
    private int LikesNum;
    private Date date;
    private String category;


    public Question_item(String name,String questionText, Date date,String category) {
        this.id = id;
        UserId = 0;
        QuestionText = questionText;
        username = name;
        CatId = 0;
        LikesNum = 0;
        this.date = date;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String name) {
        username = name;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }

    public String getCategoryText() {
        return category;
    }

    public void setCategoryText(String category) {
        this.category = category;
    }


}