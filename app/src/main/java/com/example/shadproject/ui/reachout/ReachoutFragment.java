package com.example.shadproject.ui.reachout;

import android.content.Intent;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shadproject.Login;
import com.example.shadproject.MainActivity;
import com.example.shadproject.R;
import com.example.shadproject.RecyclerItemSelectedListener;
import com.example.shadproject.TellYourStory;
import com.example.shadproject.databinding.FragmentReachoutBinding;
import com.example.shadproject.tagListAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;

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