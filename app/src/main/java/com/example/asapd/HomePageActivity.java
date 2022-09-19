package com.example.asapd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;

public class HomePageActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainMenuHomeFragment homeFragment = new MainMenuHomeFragment();
    private MainMenuSearchFragment searchFragment = new MainMenuSearchFragment();
    private MainMenuBasketFragment basketFragment = new MainMenuBasketFragment();
    private MainMenuOrderlistFragment orderlistFragment = new MainMenuOrderlistFragment();
    private MainMenuMypageFragment mypageFragment = new MainMenuMypageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, homeFragment).commitAllowingStateLoss();

        NavigationBarView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements NavigationBarView.OnItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()){
                case R.id.menu_home:
                    transaction.replace(R.id.menu_frame_layout, homeFragment).commitAllowingStateLoss();
                    break;
                case R.id.menu_search:
                    transaction.replace(R.id.menu_frame_layout, searchFragment).commitAllowingStateLoss();
                    break;
                case R.id.menu_basket:
                    transaction.replace(R.id.menu_frame_layout, basketFragment).commitAllowingStateLoss();
                    break;
                case R.id.menu_orderlist:
                    transaction.replace(R.id.menu_frame_layout, orderlistFragment).commitAllowingStateLoss();
                    break;
                case R.id.menu_mypage:
                    transaction.replace(R.id.menu_frame_layout, mypageFragment).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

}