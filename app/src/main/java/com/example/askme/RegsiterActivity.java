package com.example.askme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askme.sampledata.Question_item;

public class RegsiterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);

       // Toast.makeText(getApplicationContext(), String.valueOf(Question_item.questionList.get(0).getQuestionText()),Toast.LENGTH_LONG).show();


        //Login Page Redirect
        TextView createAccount = findViewById(R.id.haveAccountTextView);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegsiterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}