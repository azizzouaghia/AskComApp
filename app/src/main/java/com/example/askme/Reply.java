package com.example.askme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askme.sampledata.Question_item;
import com.example.askme.sampledata.cmnt_item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Reply extends AppCompatActivity {
    public String username,category,question,replyText;
    TextView usernameText,categoryText,questionText;
    EditText replyEdit;
    Button replyBtn;
    ListView cmntListView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://askcom-345a0-default-rtdb.firebaseio.com/");
    ArrayList<cmnt_item> filteredList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        usernameText = findViewById(R.id.user_name);
        categoryText = findViewById(R.id.category);
        questionText = findViewById(R.id.question_text);
        replyEdit = findViewById(R.id.reply);
        replyBtn = findViewById(R.id.replyButton);
        cmntListView = findViewById(R.id.cmntList);

   /*     for(int i =0;i<2;i++){
            cmnt_item newcmnt = new cmnt_item("re","vjvj","j","j");
            cmnt_item.cmnntList.add(newcmnt);
        }*/



        /********************Get The Data From Dashbord Intent***********************/
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        category = intent.getStringExtra("category");
        question = intent.getStringExtra("question");

        /********************Load The Data To The Cmnt***********************/
        usernameText.setText(username);
        categoryText.setText(category);
        questionText.setText(question);

        /*******************Keep only the right comments************************/
        for (cmnt_item item : cmnt_item.cmnntList) {
            if (question.equals(item.getQuestion())) {
                filteredList.add(item);
            }
        }
        initWidgets();
        setCmntsAdapter();

        /********************Insert Data Into The FireBase***********************/
        replyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("cmnts").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DatabaseReference cmntRef = databaseReference.child("cmnts");
                        String cmntId = cmntRef.push().getKey();
                        replyText = replyEdit.getText().toString();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String formattedDate = dateFormat.format(new Date());

                        cmntRef.child(cmntId).child("cmnt").setValue(replyText);
                        cmntRef.child(cmntId).child("username").setValue(data.getInstance().getUsername());
                        cmntRef.child(cmntId).child("question").setValue(question);
                        cmntRef.child(cmntId).child("date").setValue(formattedDate);

                        Toast.makeText(Reply.this, "Reply Added", Toast.LENGTH_SHORT).show();
                        cmnt_item.loadCmntsFromFirebase();
                        Intent intent = new Intent(Reply.this,DashboardActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    private void initWidgets() {
        cmntListView = findViewById(R.id.cmntList);
    }

    private void setCmntsAdapter() {
        CmntAdapter cmntAdapter = new CmntAdapter(getApplicationContext(), filteredList);
        cmntListView.setAdapter(cmntAdapter);
    }

    public void back(View v){
        finish();
    }
    public void home(View v){
        Intent intent = new Intent(Reply.this,DashboardActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }
    public void profile(View v){
        Intent intent = new Intent(Reply.this,Profile.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }
}