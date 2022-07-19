package com.example.shadproject.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shadproject.dailyCheckIn;
import com.example.shadproject.databinding.FragmentHomeBinding;
import com.example.shadproject.ui.reachout.ReachoutFragment;
import com.google.firebase.auth.FirebaseAuth;
import android.content.SharedPreferences;



import java.util.Calendar;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Button logout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        logout = binding.signout;
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
            }
        });
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);




        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String todayString = year + "" + month + "" + day;
        SharedPreferences settings = HomeFragment.this.getActivity().getSharedPreferences("PREFS", 0);

        boolean currentDay = settings.getBoolean(todayString, false);

        if (!currentDay) {

            Intent intent = new Intent(HomeFragment.this.getActivity(), dailyCheckIn.class);
            startActivity(intent);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(todayString, true);
            editor.apply();
        }

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}