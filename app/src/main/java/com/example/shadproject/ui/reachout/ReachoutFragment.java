package com.example.shadproject.ui.reachout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shadproject.databinding.FragmentReachoutBinding;

public class ReachoutFragment extends Fragment {

    private FragmentReachoutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReachoutViewModel reachoutViewModel =
                new ViewModelProvider(this).get(ReachoutViewModel.class);

        binding = FragmentReachoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}