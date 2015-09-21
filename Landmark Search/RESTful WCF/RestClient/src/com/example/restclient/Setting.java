package com.example.restclient;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public final class Setting {
	public static String uploadServer = "http://192.168.97.1:1010/UploadToServer.php";
    public static String detectServer = "http://192.168.97.1:80/RestServiceImpl/RestServiceImpl.svc/detect";

//  public static String uploadServer = "http://tmtriet.dyndns.info:1010/UploadToServer.php";
//  public static String detectServer = "http://tmtriet.dyndns.info:800/RestServiceImpl/RestServiceImpl.svc/detect";
    public static String matchingServer = "http://tmtriet.dyndns.info:800/RestServiceImpl/RestServiceImpl.svc/matching";

    public static Landmark mLandmark = null;
    public static Info mInfo = null;
    public static VideoInfo mVideo = null;
    public static ImageInfo mImage = null;
    public static Review mReview = null;

    public static int mMode = 1; // 1: Classification; 2: Template Matching
    public static int mType = 1; // 1: Book; 2: Poster

    public static void SetInfoLandMark(String name, String address, String description, String phone, String wikiKey, String type, String thumbURL){
        mLandmark = new Landmark(name, address, description, phone, wikiKey, type, thumbURL);
    }

    public static void SetInfoVideo(List<String> videos, List<String> video_decs){
        mVideo = new VideoInfo(videos, video_decs);
    }

    public static void SetInfoImage(List<String> photos, List<String> photo_decs){
        mImage = new ImageInfo(photos, photo_decs);
    }

    public static void SetInfo(String f1, String f2, String f3, String f4, String f5, String f6, String f7, String f8, String f9, String f10, String f11){
        mInfo = new Info(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11);
    }

    public static void SetPreivew(List<String> title, List<String> body){
        mReview = new Review(title, body);
    }
}