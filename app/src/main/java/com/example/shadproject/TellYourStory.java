package com.example.shadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class TellYourStory extends AppCompatActivity {
    private RecyclerView tagList;
    ArrayList<String> tags;
    String[] temp = {"a", "b", "c", "d"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tell_your_story2);

        tags = new ArrayList<>();
        tags.addAll(Arrays.asList(temp));

        RecyclerView taglist = findViewById(R.id.tagList);

        tagListAdapter adapter = new tagListAdapter(this, tags);

        taglist.setAdapter(adapter);
        taglist.setLayoutManager(new LinearLayoutManager(this));



    }
}