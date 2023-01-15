package com.example.chatapp.Models;

public class MessageModel {

      String uid ,MsgText;

    public MessageModel() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMsgText() {
        return MsgText;
    }

    public void setMsgText(String msgText) {
        MsgText = msgText;
    }

    public MessageModel(String msgText) {
        MsgText = msgText;
    }

    public MessageModel(String uid, String msgText) {
        this.uid = uid;
        MsgText = msgText;
    }
}
