package com.example.restclient;

import java.util.List;

/**
 * Created by ChauSang on 7/12/13.
 */
public class VideoInfo {
    public List<String> Videos = null;
    public List<String> Videos_Desc = null;

    public VideoInfo(List<String> videos, List<String> videos_Desc){
        Videos = videos;
        Videos_Desc = videos_Desc;
    }
}
