package com.example.shadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class TellYourStory extends AppCompatActivity implements RecyclerItemSelectedListener {
    private RecyclerView tagList;
    private EditText tagSearch, name, description, insta, wechat;
    private Button postBtn;
    private ChipGroup mChipGroup;
    ArrayList<String> selectedTags;
    ArrayList<String> tags;
    String[] temp = {"a", "b", "c", "d"};

    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference ref = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tell_your_story2);

        tags = new ArrayList<>();
        selectedTags = new ArrayList<>();
        tags.addAll(Arrays.asList(temp));

        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.TYSNameInput);
        description = findViewById(R.id.TYSDescriptionInput);
        insta = findViewById(R.id.InstaInput);
        wechat = findViewById(R.id.WeChatInput);

        postBtn = findViewById(R.id.TYSBtn);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postTYS();
            }
        });

        RecyclerView tagList = findViewById(R.id.tagList);
        tagSearch = findViewById(R.id.tagSearch);
        mChipGroup = findViewById(R.id.selectedTags);
        tagSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                String userInput = s.toString();
                ArrayList<String> newTags = new ArrayList<>();
                for(String str : tags) {
                    if(str.contains(userInput)) {
                        newTags.add(str);
                    }
                }

                tagListAdapter adapter = new tagListAdapter(TellYourStory.this, newTags, TellYourStory.this);
                tagList.setAdapter(adapter);



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        tagListAdapter adapter = new tagListAdapter(this, tags, this);

        tagList.setAdapter(adapter);
        tagList.setLayoutManager(new LinearLayoutManager(this));



    }


    @Override
    public void onItemSelected(String tag) {
        Chip chip = new Chip(this);
        selectedTags.add(tag);
        chip.setText(tag);
        chip.setCloseIconVisible(true);
        chip.setCheckable(false);
        chip.setClickable(false);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chip chip = (Chip) view;
                mChipGroup.removeView(chip);
                selectedTags.remove(tag);

            }
        });

        mChipGroup.addView(chip);
        mChipGroup.setVisibility(View.VISIBLE);


    }

    private void postTYS() {


        String seller = mAuth.getCurrentUser().getUid();
        String Name = name.getText().toString().trim();

        String Description = description.getText().toString().trim();
        ArrayList<String> Tags = selectedTags;

        String Insta = insta.getText().toString().trim();
        String WeChat = wechat.getText().toString().trim();

        TYSentry item = new TYSentry(Name, Description, seller, Tags, Insta, WeChat);

        DatabaseReference Ref = ref.child("HelpList");
        String itemID = Ref.push().getKey();
        Ref.child(itemID).setValue(item);

        Toast.makeText(TellYourStory.this, "Post Successful", Toast.LENGTH_LONG).show();

        startActivity(new Intent(TellYourStory.this, MainActivity.class));

    }
}