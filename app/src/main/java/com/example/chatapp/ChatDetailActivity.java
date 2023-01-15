package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chatapp.Adapters.MessageAdapter;
import com.example.chatapp.Models.MessageModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class ChatDetailActivity extends AppCompatActivity {


      TextView textUserName;
      FirebaseDatabase databaseChat;
      FirebaseAuth firebaseAuthChat;
      ImageView BckBtn;
      RecyclerView  recyclerViewMsg;
      EditText editTextMsg;
      ImageView buttonsend;
      FirebaseDatabase firebaseDatabaseMsg;
      ArrayList<MessageModel> modelArrayList;
      public String StringMessage;
      private byte encryptionkey[]={9,115,51,86,105,4,-31,-23,-68,88,17,20,3,-105,115,119,-53};
      private Cipher cipher,decipher;
      private SecretKeySpec spec;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);

        getSupportActionBar().hide();

       databaseChat = FirebaseDatabase.getInstance();
       firebaseAuthChat = FirebaseAuth.getInstance();

          textUserName = findViewById(R.id.txtUserName);
          recyclerViewMsg = findViewById(R.id.msg_recyclerview);
          editTextMsg = findViewById(R.id.typing_space);
          buttonsend = findViewById(R.id.send_msg_btn);

           modelArrayList = new ArrayList<MessageModel>();

             firebaseDatabaseMsg = FirebaseDatabase.getInstance();


        try {
            cipher = Cipher.getInstance("AES");
            decipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

          spec = new SecretKeySpec(encryptionkey,"AES");



        String userNameChat = getIntent().getStringExtra("usernameChat");
           textUserName.setText(userNameChat);



         String senderId = firebaseAuthChat.getUid();
         String reciverId= getIntent().getStringExtra("userid");

          BckBtn = findViewById(R.id.back_btn);



           BckBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   Intent back  = new Intent(ChatDetailActivity.this,UserList.class);
                   startActivity(back);
               }
           });


              // ArrayList<MessageModel> messageModelArrayList = new ArrayList<>();

                MessageAdapter messageAdapter = new MessageAdapter(modelArrayList,getApplicationContext());
              recyclerViewMsg.setAdapter(messageAdapter);

           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerViewMsg.setLayoutManager(linearLayoutManager);
            recyclerViewMsg.setHasFixedSize(true);

            final String SenderRomm = senderId+reciverId;
            final String reciverRoom = reciverId+senderId;

              firebaseDatabaseMsg.getReference("Chats")
                      .child(SenderRomm)
                      .addValueEventListener(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot snapshot) {

                              modelArrayList.clear();
                                for(DataSnapshot snapshotM:snapshot.getChildren()) {


                                    MessageModel model2  = snapshotM.getValue(MessageModel.class);

                                       String msgg  = model2.getMsgText().toString();

                                         String Decrypt=null;

                                    try {
                                       Decrypt = AES.decrypt(msgg);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    modelArrayList.add(new MessageModel(snapshotM.child("uid").getValue().toString()
                                    ,Decrypt));



                                }
                                 messageAdapter.notifyDataSetChanged();
                          }

                          @Override
                          public void onCancelled(@NonNull DatabaseError error) {

                          }
                      });

               buttonsend.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                          String yourMsg = editTextMsg.getText().toString();

                           String finalencrpt = yourMsg;


                       try {
                           finalencrpt = AES.encrypt(yourMsg);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }


                       final MessageModel model = new MessageModel(senderId,finalencrpt);

                          editTextMsg.setText("");

                            firebaseDatabaseMsg.getReference("Chats")
                                    .child(SenderRomm)
                                    .push()
                                    .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                      firebaseDatabaseMsg.getReference().child("Chats")
                                              .child(reciverRoom)
                                              .push()
                                              .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                          @Override
                                          public void onSuccess(Void unused) {


                                          }
                                      });

                                }
                            });
                   }
               });

     }





}