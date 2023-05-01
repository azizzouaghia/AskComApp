package com.example.askme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.askme.sampledata.Question_item;

import java.util.Date;

public class AddQuestion extends AppCompatActivity {

    private EditText questionText,categoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        initWidgets();
    }

    private void initWidgets() {
        questionText = findViewById(R.id.questionEditText);
        categoryText = findViewById(R.id.categoryEditText);
    }

    public void saveQuestion(View view){
        String question = String.valueOf(questionText.getText());
        String category = String.valueOf(categoryText.getText());
        int id = Question_item.questionList.size();
        Question_item newQuestion = new Question_item(question, new Date());
        Question_item.questionList.add(newQuestion);
        Toast.makeText(this, "Question Added", Toast.LENGTH_SHORT).show();

        Intent newQuestionIntent = new Intent(AddQuestion.this,DashboardActivity.class);
        startActivity(newQuestionIntent);
    }
}