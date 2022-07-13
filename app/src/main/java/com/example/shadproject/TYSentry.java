package com.example.shadproject;
import java.io.Serializable;


import java.util.ArrayList;

public class TYSentry implements Serializable{
    public String name;
    public String description;
    public String seller;
    public ArrayList<String> tags;
    public int value;

    public TYSentry() {

    }

    public TYSentry(String name, String description, String seller, ArrayList<String> tags)  {
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.tags = tags;
        this.value = -1;
    }
}
