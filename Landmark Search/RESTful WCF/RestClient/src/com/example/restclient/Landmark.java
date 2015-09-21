package com.example.restclient;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ChauSang on 7/3/13.
 */
public class Landmark
{
    public String Name = null;
    public String Address = null;
    public String Description = null;
    public String Phone = null;
    public String WikiKey = null;
    public String Type = null;
    public String ThumbUrl = null;

    public Landmark(String name, String address, String description, String phone, String wikiKey, String type, String thumbUrl)
    {
        Name = name;
        Address = address;
        Description = description;
        Phone = phone;
        WikiKey = wikiKey;
        Type = type;
        ThumbUrl = thumbUrl;
    }
}