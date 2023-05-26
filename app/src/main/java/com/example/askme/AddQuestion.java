package com.example.askme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.askme.sampledata.Question_item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class AddQuestion extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://askcom-345a0-default-rtdb.firebaseio.com/");
    private EditText questionText,categoryText;
    String username = "aziz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        username = data.getInstance().getUsername();
        //*********************Problem Of FireBase Delay*************************//
        /**//**//**//**/Question_item.loadQuestionsFromFirebase();//**//**//**//**/
        //*********************/Problem Of FireBase Delay************************//
        setContentView(R.layout.activity_add_question);
        initWidgets();
    }

    private void initWidgets() {
        questionText = findViewById(R.id.questionEditText);
        categoryText = findViewById(R.id.category);
    }

    public void saveQuestion(View view){
        String question = String.valueOf(questionText.getText());
        String category = String.valueOf(categoryText.getText());
        int id = Question_item.questionList.size();
        Question_item newQuestion = new Question_item(username,question, new Date(),category);

        /**************************************InsertIntoFireBase***********************************************/
        databaseReference.child("question").addListenerForSingleValueEvent(new ValueEventListener() {
            DatabaseReference usersRef = databaseReference.child("question");
            String qstId = usersRef.push().getKey();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                usersRef.child(qstId).child("text").setValue(question);
                usersRef.child(qstId).child("username").setValue(username);
                usersRef.child(qstId).child("category").setValue(category);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /**************************************InsertIntoFireBase***********************************************/

        Question_item.questionList.add(newQuestion);
        Toast.makeText(this, "Question Added", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void back(View v){
        finish();
    }
    public void home(View v){
        Intent intent = new Intent(AddQuestion.this,DashboardActivity.class);
        startActivity(intent);
        finish();
    }
    public void profile(View v){
        Intent intent = new Intent(AddQuestion.this,Profile.class);
        startActivity(intent);
        finish();
    }
}