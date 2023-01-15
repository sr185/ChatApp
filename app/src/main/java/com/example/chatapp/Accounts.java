package com.example.chatapp;

public class Accounts {

    String usrnm;
      String em;
      String pas;
      String Userid;

    public Accounts(String usrnm, String em, String pas, String userid) {
        this.usrnm = usrnm;
        this.em = em;
        this.pas = pas;
        this.Userid = userid;
    }

    public String getUserid(String key) {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getUsrnm() {
        return usrnm;
    }

    public void setUsrnm(String usrnm) {
        this.usrnm = usrnm;
    }

    public Accounts(String usrnm, String em, String pas) {
        this.usrnm=usrnm;
        this.em = em;
        this.pas = pas;

    }

    public Accounts(){


      }

    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

}
