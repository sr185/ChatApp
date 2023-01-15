package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


          new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

               Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();

               Intent intent1  = new Intent(getApplicationContext(),MainActivity2.class);
               startActivity(intent1);

           }
       },2000);


    }
}