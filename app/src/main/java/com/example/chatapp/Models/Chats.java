package com.example.chatapp.Models;

public class Chats {

    //public int length;
    private String image;

    private String lastMessage;
    private String lastMessageTime;

    public Chats() {
    }




    public Chats(String lastMessage, String lastMessageTime) {

        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }

    public Chats(String image, String name, String lastMessage, String lastMessageTime) {
        this.image = image;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }


     public String getImage() {
       return image;
    }

    public void setImage(String image) {
        this.image = image;    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }
}
