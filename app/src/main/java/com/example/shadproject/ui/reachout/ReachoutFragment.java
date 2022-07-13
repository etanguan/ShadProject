package com.example.shadproject.ui.reachout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;


import com.example.shadproject.BoostString;
import com.example.shadproject.Login;
import com.example.shadproject.MainActivity;
import com.example.shadproject.R;
import com.example.shadproject.RecyclerItemSelectedListener;
import com.example.shadproject.TYSentry;
import com.example.shadproject.TellYourStory;
import com.example.shadproject.databinding.FragmentReachoutBinding;
import com.example.shadproject.helpList;
import com.example.shadproject.tagListAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;

public class ReachoutFragment extends Fragment implements RecyclerItemSelectedListener {

    private Button tellyourstory, reachOutBtn;
    private ChipGroup mChipGroup;
    EditText tagSearch;
    ArrayList<String> selectedTags;
    ArrayList<String> tags;
    String[] temp = {"a", "b", "c", "d"};

    private FragmentReachoutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReachoutViewModel reachoutViewModel =
                new ViewModelProvider(this).get(ReachoutViewModel.class);

        binding = FragmentReachoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tellyourstory = binding.TellYourStoryButton;
        tellyourstory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReachoutFragment.this.getActivity(), TellYourStory.class);
                startActivity(intent);
            }
        });

        tags = new ArrayList<>();
        selectedTags = new ArrayList<>();
        tags.addAll(Arrays.asList(temp));

        RecyclerView tagList = binding.reachoutTagList;
        tagSearch = binding.reachoutTagSearch;
        mChipGroup = binding.reachoutSelectedTags;
        reachOutBtn = binding.reachOutBtn;
        reachOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*final ArrayList<String> nameList = new ArrayList<>();
                final ArrayList<String> desList = new ArrayList<>();
                final ArrayList<ArrayList<String>> tagList = new ArrayList<>();*/
                final ArrayList<TYSentry> entryList = new ArrayList<>();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("HelpList");
                reference.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            TYSentry item = dataSnapshot.getValue(TYSentry.class);
                            entryList.add(item);
                        }

                        //final ArrayList<Integer> index = new ArrayList<>();

                        for (int i = 0; i < entryList.size();i++){
                            int counter = 0;
                            for (String tag : selectedTags){
                                if (entryList.get(i).tags.contains(tag)){
                                    counter ++;

                                }
                            }
                            entryList.get(i).value = counter;

                        }

                        Collections.sort(entryList, new Comparator<TYSentry>() {
                            @Override
                            public int compare(TYSentry t0, TYSentry t1) {
                                return t1.value-t0.value;

                            }
                        });
                        Intent intent = new Intent(ReachoutFragment.this.getActivity(), helpList.class);
                        Bundle args = new Bundle();
                        args.putSerializable("entryList", (Serializable) entryList);
                        intent.putExtras(args);
                        startActivity(intent);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

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

                tagListAdapter adapter = new tagListAdapter(ReachoutFragment.this.getActivity(), newTags, ReachoutFragment.this);
                tagList.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        tagListAdapter adapter = new tagListAdapter(ReachoutFragment.this.getActivity(), tags, ReachoutFragment.this);

        tagList.setAdapter(adapter);
        tagList.setLayoutManager(new LinearLayoutManager(ReachoutFragment.this.getActivity()));







        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(String tag) {
        Chip chip = new Chip(ReachoutFragment.this.getActivity());
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
}