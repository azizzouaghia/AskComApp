package com.example.askme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editUser extends AppCompatActivity {

    String username,createdDate,email , UpdatedUser,UpdatedMail;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://askcom-345a0-default-rtdb.firebaseio.com/");
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        createdDate = intent.getStringExtra("createdDate");
        email = intent.getStringExtra("email");

        TextView usernameText = findViewById(R.id.username);
        TextView dateText = findViewById(R.id.createdDate);
        EditText usernameEdit = findViewById(R.id.usernameField);
        EditText emailEdit = findViewById(R.id.emailField);
        Button update = findViewById(R.id.updateButton);

        usernameText.setText(username);
        dateText.setText(createdDate);
        usernameEdit.setText(username);
        emailEdit.setText(email);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child("users").orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                            UpdatedUser = usernameEdit.getText().toString();
                            UpdatedMail = emailEdit.getText().toString();

                            userSnapshot.getRef().child("username").setValue(UpdatedUser);
                            userSnapshot.getRef().child("email").setValue(UpdatedMail);

                            Intent intent = new Intent(editUser.this,Profile.class);
                            data.getInstance().setUsername(UpdatedUser);
                            Toast.makeText(editUser.this, "User Updated Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }

    public void back(View v){
        finish();
    }
    public void home(View v){
        Intent intent = new Intent(editUser.this,DashboardActivity.class);
        startActivity(intent);
        finish();
    }
    public void profile(View v){
        Intent intent = new Intent(editUser.this,Profile.class);
        startActivity(intent);
        finish();
    }
}