package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserList extends AppCompatActivity {


     TabLayout tabLayout;
     FrameLayout frmLayout;
     Fragment fragment = null;
     FragmentManager fragmentManager;
     FragmentTransaction fragmentTransaction;
     ImageView lgt;
     FirebaseAuth firebaseAuthLgt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

         getSupportActionBar().hide();

         tabLayout = findViewById(R.id.tablayout);
         frmLayout = findViewById(R.id.frameLayout);
         lgt = findViewById(R.id.Logout);

           firebaseAuthLgt = FirebaseAuth.getInstance();

           lgt.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                      firebaseAuthLgt.signOut();
                   Intent intentLgt = new Intent(UserList.this,MainActivity2.class);
                   startActivity(intentLgt);

               }
           });

         fragment = new ChatFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ChatFragment();
                        break;
                    case 1:
                        fragment = new StatusFragment();
                        break;
                    case 2:
                        fragment = new CallFragment();
                        break;

                }

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}