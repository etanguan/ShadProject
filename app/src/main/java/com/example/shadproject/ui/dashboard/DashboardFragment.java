package com.example.shadproject.ui.dashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shadproject.CustomBaseAdapter;
import com.example.shadproject.CustomBaseAdapter2;
import com.example.shadproject.TYSentry;
import com.example.shadproject.TellYourStory;
import com.example.shadproject.databinding.FragmentDashboardBinding;
import com.example.shadproject.helpList;
import com.example.shadproject.storyTellYourStory;
import com.example.shadproject.ui.reachout.ReachoutFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    ListView listView;
    Button tysbtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;

        tysbtn = binding.StoryTellYourStoryButton;
        tysbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardFragment.this.getActivity(), storyTellYourStory.class);
                startActivity(intent);
            }
        });

        final ArrayList<TYSentry> entryList = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("HelpList");
        DatabaseReference reference2 = database.getReference("StoryList");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    TYSentry item = dataSnapshot.getValue(TYSentry.class);
                    entryList.add(item);
                }
                reference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                            TYSentry item = dataSnapshot.getValue(TYSentry.class);
                            entryList.add(item);
                        }
                        Collections.reverse(entryList);
                        CustomBaseAdapter2 customBaseAdapter = new CustomBaseAdapter2(getActivity(), entryList);
                        listView = binding.storyListId;
                        listView.setAdapter(customBaseAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}