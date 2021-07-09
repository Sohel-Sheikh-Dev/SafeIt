package com.example.safeit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.safeit.Activity.InsertPassword;
import com.example.safeit.Adapter.FragmentAdapter;
import com.example.safeit.Fragments.AllItemFrag;
import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    com.google.android.material.tabs.TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_list_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_lock_open_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_note_add_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_person_24));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }


}