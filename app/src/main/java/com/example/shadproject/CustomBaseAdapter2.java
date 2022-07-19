package com.example.shadproject;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class CustomBaseAdapter2 extends BaseAdapter {
    Context context;
    ArrayList<TYSentry> entryList;
    LayoutInflater inflater;





    public CustomBaseAdapter2(Context ctx, ArrayList<TYSentry> entryList) {
        this.context = ctx;
        this.entryList = entryList;
        inflater = (LayoutInflater.from(ctx));



    }

    @Override
    public int getCount() {
        return entryList.size();

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.story_list_item, null);

        TextView description = view.findViewById(R.id.listDescriptionText2);
        TextView name = view.findViewById(R.id.listNameText2);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();


        if (i < entryList.size()) {
            description.setText(entryList.get(i).description);
            if (!Objects.equals(entryList.get(i).name, "")) {
                name.setText(entryList.get(i).name);
            }
            else{
                name.setText("Anonymous");
            }





        }else {
            return view;
        }
        return view;
    }
}
