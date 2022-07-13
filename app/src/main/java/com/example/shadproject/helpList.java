package com.example.shadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class helpList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_list);

        Intent intent = getIntent();
        ArrayList<TYSentry> entryList = (ArrayList<TYSentry>) intent.getSerializableExtra("entryList");
        ListView listView = findViewById(R.id.helpListId);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(this, entryList);

        listView.setAdapter(customBaseAdapter);
    }
}