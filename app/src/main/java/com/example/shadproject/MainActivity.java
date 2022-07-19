package com.example.shadproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.shadproject.ui.reachout.ReachoutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.widget.Toast;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.shadproject.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements RecyclerItemSelectedListener {
    boolean signedIn = false;
    private FirebaseAuth mAuth;
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_reachout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        /*Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH)-7;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String todayString = year + "" + month + "" + day;
        SharedPreferences settings = getSharedPreferences("PREFS", 0);

        boolean currentDay = settings.getBoolean(todayString, false);

        if (!currentDay) {

            Intent intent = new Intent(this, dailyCheckIn.class);
            startActivity(intent);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(todayString, true);
            editor.apply();
        }*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser curUser = mAuth.getCurrentUser();
        if (curUser==null) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }else{
            String email = curUser.getEmail();
            Toast.makeText(MainActivity.this, email, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(String string) {

    }
}