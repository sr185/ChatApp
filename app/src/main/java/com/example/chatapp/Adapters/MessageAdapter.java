package com.example.chatapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.Models.MessageModel;
import com.example.chatapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter {

      ArrayList<MessageModel> messageModels;
      Context contextMsg;
    FirebaseAuth firebaseAuthMsg = FirebaseAuth.getInstance();

    public MessageAdapter(ArrayList<MessageModel> messageModels, Context contextMsg) {
        this.messageModels = messageModels;
        this.contextMsg = contextMsg;

    }


    final int SENDER_VIEWHOLDER = 0;
    final int RECEIVER_VIEWHOLDER = 1;

    @Override
    public int getItemViewType(int position) {

        if (messageModels.get(position).getUid().equals(firebaseAuthMsg.getUid()))
            return SENDER_VIEWHOLDER;
        else
            return RECEIVER_VIEWHOLDER;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         if(viewType==SENDER_VIEWHOLDER){
             View view = LayoutInflater.from(contextMsg).inflate(R.layout.sample_sender, parent, false);
             return new OutgoingViewholder(view);
         }
          else
         {
             View view = LayoutInflater.from(contextMsg).inflate(R.layout.sample_reciver, parent, false);
             return new ReciverViewholder(view);
         }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

         if(holder.getClass()==OutgoingViewholder.class){
             ((OutgoingViewholder) holder).outgoingMsg.setText(messageModels.get(position).getMsgText());
         }
         else
         {
             ((ReciverViewholder) holder).incomingMsg.setText(messageModels.get(position).getMsgText());
         }

    }

    @Override
    public int getItemCount() {

       return  messageModels.size();
    }

    public class OutgoingViewholder extends RecyclerView.ViewHolder {

        TextView outgoingMsg;


        public OutgoingViewholder(@NonNull View itemView) {
            super(itemView);

            outgoingMsg = itemView.findViewById(R.id.outgoing_msg);

        }
    }


    public class ReciverViewholder extends RecyclerView.ViewHolder {

        TextView incomingMsg;


        public ReciverViewholder(@NonNull View itemView) {
            super(itemView);

            incomingMsg = itemView.findViewById(R.id.incoming_msg);
        }
    }
}
