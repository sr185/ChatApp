package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    TextView txt2;
    EditText ett1;
    EditText ett2;
    Button loginbtn;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().hide();

        txt2 = findViewById(R.id.textView2);

        ett1 = findViewById(R.id.email);
        ett2 = findViewById(R.id.password);
        loginbtn = findViewById(R.id.button);

        firebaseAuth = FirebaseAuth.getInstance();


        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inten = new Intent(MainActivity2.this, Signup.class);
                startActivity(inten);

            }
        });


         loginbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                  String x  = ett1.getText().toString().trim();
                  String y =  ett2.getText().toString().trim();

                   if(x.isEmpty()){

                        Toast.makeText(getApplicationContext(),"Please enter your email",Toast.LENGTH_SHORT).show();
                   }
                  if(y.isEmpty())
                   {
                       Toast.makeText(getApplicationContext(),"Please enter your password",Toast.LENGTH_SHORT).show();
                   }


                    firebaseAuth.signInWithEmailAndPassword(x,y).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                          if(task.isSuccessful()){

                               Toast.makeText(getApplicationContext(),"Login Sucessfull",Toast.LENGTH_SHORT).show();

                               

                              Intent logininten = new Intent(MainActivity2.this,UserList.class);
                               startActivity(logininten);
                          }
                          else
                          {
                              Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                          }


                        }
                    });
             }
         });


         if(firebaseAuth.getCurrentUser()!=null){

              Intent intentimp = new Intent(MainActivity2.this,UserList.class);
              startActivity(intentimp);
         }
    }



}