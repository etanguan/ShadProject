package com.example.shadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class storyTellYourStory extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private EditText name, description;
    private Button postBtn;
    private FirebaseAuth mAuth;


    DatabaseReference ref = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_tell_your_story);

        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.StoryTYSNameInput);
        description = findViewById(R.id.StoryTYSDescriptionInput);
        postBtn = findViewById(R.id.storyTYSBtn);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postTYS();
            }
        });


    }

    private void postTYS() {


        String seller = mAuth.getCurrentUser().getUid();
        String Name = name.getText().toString().trim();

        String Description = description.getText().toString().trim();
        ArrayList<String> Tags = null;

        String Insta = null;
        String WeChat = null;

        TYSentry item = new TYSentry(Name, Description, seller, Tags, Insta, WeChat);

        DatabaseReference Ref = ref.child("StoryList");
        String itemID = Ref.push().getKey();
        Ref.child(itemID).setValue(item);

        Toast.makeText(storyTellYourStory.this, "Post Successful", Toast.LENGTH_LONG).show();

        startActivity(new Intent(storyTellYourStory.this, MainActivity.class));

    }
}