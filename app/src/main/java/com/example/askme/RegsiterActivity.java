package com.example.askme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askme.sampledata.Question_item;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegsiterActivity extends AppCompatActivity {



    EditText usernameE,emailE,passwordE;
    Button registerButton;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://askcom-345a0-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);

        //*********************Create Account*******************************//
        usernameE = findViewById(R.id.usernameEditText);
        emailE = findViewById(R.id.emailEditText);
        passwordE = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username,email,password;
                username = String.valueOf(usernameE.getText());
                email = String.valueOf(emailE.getText());
                password = String.valueOf(passwordE.getText());

                if (TextUtils.isEmpty(email)){ //Check if email is empty
                    Toast.makeText(RegsiterActivity.this, "ya bro ekteb l mail yehdik", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(username)){ //Check if name is empty
                    Toast.makeText(RegsiterActivity.this, "ya3ni t7eb tud5el ma8ir ism nice nice", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){ //Check if pass is empty
                    Toast.makeText(RegsiterActivity.this, "ya3ni ktebet l mail wl username w pass le", Toast.LENGTH_SHORT).show();
                    return;
                }

                registerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                DatabaseReference usersRef = databaseReference.child("users");
                                String userId = usersRef.push().getKey();

                                //Check The Username/Email
                                boolean usernameExists = false;
                                boolean emailExists = false;
                                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                    String childUsername = childSnapshot.child("username").getValue(String.class);
                                    String childEmail = childSnapshot.child("email").getValue(String.class);
                                    if (childUsername != null && childUsername.equals(username)) {
                                        usernameExists = true;
                                    }
                                    if (childEmail != null && childEmail.equals(email)) {
                                        emailExists = true;
                                    }
                                }
                                if(usernameExists   || emailExists){

                                    Toast.makeText(RegsiterActivity.this, "This UserName/Email is already used", Toast.LENGTH_SHORT).show();

                                } else {

                                    usersRef.child(userId).child("username").setValue(username);
                                    usersRef.child(userId).child("email").setValue(email);
                                    usersRef.child(userId).child("password").setValue(password);
                                    Toast.makeText(RegsiterActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
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
        });
        //*********************/Create Account*******************************//



        //*******************Login Page Redirect****************************//
        TextView createAccount = findViewById(R.id.haveAccountTextView);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegsiterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //*******************/Login Page Redirect****************************//
    }
}