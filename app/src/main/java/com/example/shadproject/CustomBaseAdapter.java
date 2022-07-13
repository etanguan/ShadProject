package com.example.shadproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<TYSentry> entryList;
    LayoutInflater inflater;



    public CustomBaseAdapter(Context ctx, ArrayList<TYSentry> entryList) {
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
        view = inflater.inflate(R.layout.helplistitem, null);
        TextView name = view.findViewById(R.id.listNameText);
        TextView description = view.findViewById(R.id.listDescriptionText);
        TextView tag = view.findViewById(R.id.listTagText);

        if (i < entryList.size()) {
            name.setText(entryList.get(i).name);
            description.setText(entryList.get(i).description);
            String tagString = String.join(", ", entryList.get(i).tags);
            tag.setText(tagString);

        }else {
            return view;
        }
        return view;
    }
}
