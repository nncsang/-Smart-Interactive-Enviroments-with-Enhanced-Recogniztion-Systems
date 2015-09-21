package com.example.restclient;

import java.util.List;

/**
 * Created by ChauSang on 7/12/13.
 */
public class ImageInfo {
    public List<String> Photos = null;
    public List<String> Photos_Desc = null;
    public ImageInfo(List<String> photos, List<String> photos_Desc){
        Photos = photos;
        Photos_Desc = photos_Desc;
    }
}
