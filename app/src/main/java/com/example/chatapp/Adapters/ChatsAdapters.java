package com.example.chatapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.Accounts;
import com.example.chatapp.ChatDetailActivity;
import com.example.chatapp.R;

import java.util.ArrayList;

public class ChatsAdapters extends RecyclerView.Adapter<ChatsAdapters.ViewHolder> {

      public ArrayList<Accounts> accountsArrayList;
      public Context context;

    public ChatsAdapters(ArrayList<Accounts> accountsArrayList, Context context) {
        this.accountsArrayList = accountsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatsAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

          View view = LayoutInflater.from(context).inflate(R.layout.chatrows,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsAdapters.ViewHolder holder, int position) {

         Accounts accounts = accountsArrayList.get(position);

          holder.usernameChat.setText(accounts.getUsrnm());

          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Intent intentChat = new Intent(context,ChatDetailActivity.class);
                  intentChat.putExtra("usernameChat",accounts.getUsrnm());
                  intentChat.putExtra("userid",accounts.getUserid("Users"));
                  context.startActivity(intentChat);
              }
          });
    }

    @Override
    public int getItemCount() {
        return accountsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         public TextView usernameChat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

              usernameChat = itemView.findViewById(R.id.tvName);
        }
    }
}
