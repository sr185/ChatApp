package com.example.chatapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chatapp.Adapters.ChatsAdapters;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatFragment extends Fragment {

         RecyclerView recyclerView;
        public FirebaseDatabase firebaseDatabase4;
         public   DatabaseReference databaseReference4;
         public  ArrayList<Accounts> accountsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chat, container, false);


            firebaseDatabase4 = FirebaseDatabase.getInstance();
            accountsList = new ArrayList<>();

           recyclerView  = v.findViewById(R.id.rvChats);
           recyclerView.setHasFixedSize(true);
           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
           recyclerView.setLayoutManager(linearLayoutManager);

       databaseReference4  = firebaseDatabase4.getReference().child("Userinfo").child("Users");

       databaseReference4.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {

                   accountsList.clear();
                   for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                          Accounts accounts2 =dataSnapshot.getValue(Accounts.class);
                          accounts2.setUserid(dataSnapshot.getKey());
                          accountsList.add(accounts2);
                    }

                     ChatsAdapters chatsAdapters = new ChatsAdapters(accountsList,getContext());
                     recyclerView.setAdapter(chatsAdapters);
                     chatsAdapters.notifyDataSetChanged();
               }

               @Override
              public void onCancelled(@NonNull DatabaseError error) {

                   Toast.makeText(getContext() ,"Error", Toast.LENGTH_SHORT).show();

               }
          });

          return  v;
    }
}