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

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<TYSentry> entryList;
    LayoutInflater inflater;
    Dialog contactPopup;




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
        Button contactBtn = view.findViewById(R.id.contactButton);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        ClipboardManager myClipboard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);



        if (i < entryList.size()) {
            if (!Objects.equals(entryList.get(i).name, "")) {
                name.setText(entryList.get(i).name);
            }
            else{
                name.setText("Anonymous");
            }
            description.setText(entryList.get(i).description);
            String tagString = String.join(", ", entryList.get(i).tags);
            tag.setText(tagString);
            contactBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    contactPopup = new Dialog(context);
                    contactPopup.setContentView(R.layout.contact_popup);
                    contactPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    contactPopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    TextView instaContact = (TextView) contactPopup.findViewById(R.id.InstaContact);
                    TextView WeChatContact = (TextView) contactPopup.findViewById(R.id.WeChatContact);

                    instaContact.setText(entryList.get(i).insta);
                    WeChatContact.setText(entryList.get(i).wechat);


                    instaContact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ClipData myClip = ClipData.newPlainText("simple text", instaContact.getText().toString());
                            myClipboard.setPrimaryClip(myClip);
                            Toast.makeText(context.getApplicationContext(), "Copied to Clipboard", Toast.LENGTH_SHORT).show();


                                }
                            });

                    WeChatContact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ClipData myClip = ClipData.newPlainText("simple text", WeChatContact.getText().toString());
                            myClipboard.setPrimaryClip(myClip);
                            Toast.makeText(context.getApplicationContext(), "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        }
                    });

                    contactPopup.show();

                }
            });



        }else {
            return view;
        }
        return view;
    }
}
