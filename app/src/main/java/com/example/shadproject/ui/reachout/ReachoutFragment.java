package com.example.shadproject.ui.reachout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shadproject.Login;
import com.example.shadproject.MainActivity;
import com.example.shadproject.TellYourStory;
import com.example.shadproject.databinding.FragmentReachoutBinding;

public class ReachoutFragment extends Fragment {

    private Button tellyourstory;

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


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}