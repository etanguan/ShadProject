package com.example.shadproject;

import java.util.ArrayList;

public class TYSentry {
    public String name;
    public String description;
    public String seller;
    public ArrayList<String> tags;

    public TYSentry() {

    }

    public TYSentry(String name, String description, String seller, ArrayList<String> tags) {
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.tags = tags;
    }
}
