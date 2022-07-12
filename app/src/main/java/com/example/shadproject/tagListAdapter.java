package com.example.shadproject;


import android.content.Context;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class tagListAdapter extends RecyclerView.Adapter<tagListAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> taglist;
    private RecyclerItemSelectedListener itemSelectedListener;

    public tagListAdapter(Context context, ArrayList<String> taglist) {
        this.context = context;
        this.taglist = taglist;
        itemSelectedListener = (TellYourStory)context;

    }
    @NonNull
    @Override
    public tagListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.taglistitem, parent, false);

        return new tagListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull tagListAdapter.MyViewHolder holder, int position) {
        holder.tag.setText(taglist.get(position));

    }

    @Override
    public int getItemCount() {
        return taglist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout RootView;

        TextView tag;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tagName);
            RootView = itemView.findViewById(R.id.rootView);
            RootView.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            itemSelectedListener.onItemSelected(taglist.get(getAdapterPosition()));

        }
    }

    public void updatelist(ArrayList<String> newtags) {
        taglist.clear();
        taglist.addAll(newtags);
        notifyDataSetChanged();
    }



}
