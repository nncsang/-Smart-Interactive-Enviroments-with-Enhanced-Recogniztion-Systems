package com.example.restclient;

import java.util.List;

/**
 * Created by ChauSang on 7/12/13.
 */
public class Review {
    List<String> mTitle = null;
    List<String> mBody = null;

    public Review(List<String> title, List<String> body){
        mTitle = title;
        mBody = body;
    }
}
