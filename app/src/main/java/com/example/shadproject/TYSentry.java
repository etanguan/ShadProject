package com.example.shadproject;
import java.io.Serializable;


import java.util.ArrayList;

public class TYSentry implements Serializable{
    public String name;
    public String description;
    public String seller;
    public ArrayList<String> tags;
    public String insta;
    public String wechat;
    public int value;

    public TYSentry() {

    }

    public TYSentry(String name, String description, String seller, ArrayList<String> tags, String insta, String wechat)  {
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.tags = tags;
        this.insta = insta;
        this.wechat = wechat;
        this.value = -1;
    }
}
