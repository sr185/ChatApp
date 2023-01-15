package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

     EditText e1,e2,e3;
     Button buttonS;
     FirebaseAuth firebaseAuth2;
     FirebaseDatabase firebaseDatabase2;
      DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

          getSupportActionBar().hide();

            e1 = findViewById(R.id.nm);
            e2 = findViewById(R.id.emailad);
            e3 = findViewById(R.id.passwordS);

            buttonS = findViewById(R.id.button2);

            firebaseAuth2 = FirebaseAuth.getInstance();
        firebaseDatabase2 = FirebaseDatabase.getInstance();
        databaseReference  = firebaseDatabase2.getReference().child("Userinfo");



        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newname = e1.getText().toString().trim();
                String a = e2.getText().toString().trim();
                String b = e3.getText().toString().trim();


                if (a.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                }
                if (b.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                if (newname.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
                }



                firebaseAuth2.createUserWithEmailAndPassword(a, b).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "You are Registered", Toast.LENGTH_SHORT).show();

                             Accounts UsersAccounts = new Accounts(newname,a,b);
                             String id = task.getResult().getUser().getUid();
                             databaseReference.child("Users").child(id).setValue(UsersAccounts);

                            Intent userlist = new Intent(Signup.this, UserList.class);
                            startActivity(userlist);


                        } else
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

                    }
                });

            }

           //  private void storeData() {

              //   String k = e1.getText().toString();
                // String l = e2.getText().toString();
                // String pss = e3.getText().toString();


                // Accounts accounts = new Accounts(k, l, pss);
                // databaseReference.push().setValue(accounts);

             // }
               });


    }


}