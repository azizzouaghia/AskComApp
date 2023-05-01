package com.example.askme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.askme.sampledata.Question_item;

import java.util.Date;

public class DashboardActivity extends AppCompatActivity {

    private ListView questionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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

    public void newQuestion(View view){
        Intent newQuestionIntent = new Intent(DashboardActivity.this,AddQuestion.class);
        startActivity(newQuestionIntent);
    }
}
