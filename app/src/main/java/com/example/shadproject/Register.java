package com.example.shadproject;

import android.app.Activity;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shadproject.ui.dashboard.DashboardFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText editUsernameName, editLastName, editEmail, editPassword;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        editUsernameName = (EditText) findViewById(R.id.RegisterUsernameInput);
        editEmail = (EditText) findViewById(R.id.EmailInput);
        editPassword = (EditText) findViewById(R.id.RegisterPasswordInput);

        submit = (Button) findViewById(R.id.RegisterCreateBtn);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RegisterCreateBtn:
                registerUser();
        }
    }

    private void registerUser() {
        String username = editUsernameName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String Insta = "@";
        String WeChat = "@";


        if (username.isEmpty()) {
            editUsernameName.setError("First name required.");
            editUsernameName.requestFocus();
            return;
        }



        if (email.isEmpty()) {
            editEmail.setError("Email required.");
            editEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Must enter valid email");
            editEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editPassword.setError("Password required.");
            editEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editPassword.setError("Password must be longer than 6 characters.");
            editEmail.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(username, email, Insta, WeChat);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Toast.makeText(Register.this, "User has been registered.", Toast.LENGTH_LONG).show();
                                                FirebaseUser fuser = mAuth.getCurrentUser();

                                                fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(Register.this, "Email Verification Sent.", Toast.LENGTH_LONG).show();

                                                    }
                                                });

                                                Intent intent = new Intent(Register.this, Login.class);
                                                startActivity(intent);

                                            }else{
                                                Toast.makeText(Register.this, "Failed to register User", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(Register.this, "Failed to register User", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}