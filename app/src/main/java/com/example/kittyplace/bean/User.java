package com.example.kittyplace.bean;

import java.io.Serializable;

public class User implements Serializable{

    private int userId;
    private String userName;
    private String userLastname;
    private String userEmail;
    private String userLogin;
    private String userPasswd;
    private float userWallet;

    public User() {
        this.userId = 0;
        this.userName = "";
        this.userLastname = "";
        this.userEmail = "";
        this.userLogin = "";
        this.userPasswd = "";
        this.userWallet = 0;
    }

    public User(int id, String name, String lastname, String email, String login, String passwd) {
        this.userId = id;
        this.userName = name;
        this.userLastname = lastname;
        this.userEmail = email;
        this.userLogin = login;
        this.userPasswd = passwd;
        this.userWallet = 0;
    }

    public User(int id, String name, String lastname, String email, String login, String passwd, float wallet) {
        this.userId = id;
        this.userName = name;
        this.userLastname = lastname;
        this.userEmail = email;
        this.userLogin = login;
        this.userPasswd = passwd;
        this.userWallet = wallet;
    }

    public User(User u) {
        this.userId = u.userId;
        this.userName = u.userName;
        this.userLastname = u.userLastname;
        this.userEmail = u.userEmail;
        this.userLogin = u.userLogin;
        this.userPasswd = u.userPasswd;
        this.userWallet = u.userWallet;
    }

    public int getUserId(){return userId;}
    public String getUserName(){return userName;}
    public String getUserLastName(){return userLastname;}
    public String getUserEmail(){return userEmail;}
    public String getUserLogin(){return userLogin;}
    public String getUserPasswd() {return userPasswd;}
    public float getUserWallet() {return userWallet;}
    public User getUser(){return this;}

    public void setUserId(int id){this.userId = id;}
    public void setUserName(String name){this.userName = name;}
    public void setUserLastname(String lastname){this.userLastname = lastname;}
    public void setUserEmail(String email){this.userEmail = email;}
    public void setUserLogin(String login){this.userLogin = login;}
    public void setUserPasswd(String passwd){this.userPasswd = passwd;}
    public void setUserWallet(float wallet){this.userWallet = wallet;}

    public String toString() {

        String string = "User id = " + this.userId + "; name = " + this.userName
                + "; lastname = " + this.userLastname + "; e-mail = " + this.userEmail
                + "; login = " + this.userLogin + "; password = " + this.userPasswd
                + "; wallet = " + this.userWallet + ";";

        return string;
    }
}