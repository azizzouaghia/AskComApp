package com.example.askme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.askme.sampledata.Question_item;

import java.util.Date;

public class DashboardActivity extends AppCompatActivity {

    private ListView questionListView;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        username = data.getInstance().getUsername();
        initWidgets();
        setQuestionAdapter();


    }
    @Override
    protected void onResume() {
        super.onResume();
        initWidgets();
        setQuestionAdapter();
    }
    private void initWidgets() {
        questionListView = findViewById(R.id.question_list);
    }

    private void setQuestionAdapter() {
        QuestionAdapter questionAdapter = new QuestionAdapter(getApplicationContext(), Question_item.questionList);
        questionListView.setAdapter(questionAdapter);
    }

    //*****************************Profile***************************************//
    public void profile(View view) {
        Intent intent = new Intent(DashboardActivity.this, Profile.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
    //*****************************/Profile**************************************//


    //*****************************Back***************************************//
    public void back(View view) {
        Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
        startActivity(intent);
    }
    //*****************************/Back**************************************//


    //*****************************newQuestion***************************************//
    public void newQuestion(View view){
        Intent newQuestionIntent = new Intent(DashboardActivity.this,AddQuestion.class);
        startActivity(newQuestionIntent);
    }
    //*****************************/newQuestion**************************************//
}