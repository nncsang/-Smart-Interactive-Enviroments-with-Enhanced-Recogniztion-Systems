package com.example.restclient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.*;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

class HttpPostAsync extends AsyncTask<String, Integer, Boolean>{
    Activity activity;

    private ImageView mUpAnimView = null;
    private ImageView mDownAnimView = null;

    private LinearLayout mMenu = null;
    private ImageButton mLandmarkIcon = null;
    private TextView mLandmarkName = null;
    private int screenHeight, viewHeight;
    private long movingDuration;
    private Animation moveDown, moveUp;
    private boolean finished;

    private boolean isFound = false;
    private boolean isUploaded = false;
    private boolean isCanGetResult = false;

    public HttpPostAsync( Activity activity, ImageView upAnimView, ImageView downAnimView, LinearLayout menu, ImageButton landmarkIcon, TextView landmarkName){
        this.activity = activity;
        this.mUpAnimView = upAnimView;
        this.mDownAnimView = downAnimView;
        this.mMenu = menu;
        this.mLandmarkIcon = landmarkIcon;
        this.mLandmarkName = landmarkName;
        finished = true;

        Display display = ((WindowManager)activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        screenHeight = display.getHeight();
        viewHeight = downAnimView.getHeight();
        movingDuration = screenHeight*3; // 3 milliseconds per pixel

        moveDown = new TranslateAnimation(0, 0, -1*viewHeight, screenHeight);
        moveDown.setDuration(movingDuration);
        moveDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!finished){
                    mDownAnimView.setVisibility(View.INVISIBLE);
                    mUpAnimView.setVisibility(View.VISIBLE);
                    mUpAnimView.startAnimation(moveUp);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        moveUp = new TranslateAnimation(0, 0, screenHeight, -1*viewHeight);
        moveUp.setDuration(movingDuration);
        moveUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(!finished){
                    mUpAnimView.setVisibility(View.INVISIBLE);
                    mDownAnimView.setVisibility(View.VISIBLE);
                    mDownAnimView.startAnimation(moveDown);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

	@Override
	protected void onPostExecute(Boolean k){
        finished = true;
        mDownAnimView.clearAnimation();
        mDownAnimView.setVisibility(View.INVISIBLE);
        mUpAnimView.clearAnimation();
        mUpAnimView.setVisibility(View.INVISIBLE);

        mMenu.setVisibility(View.VISIBLE);

        mLandmarkIcon.setVisibility(View.VISIBLE);
        ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1);
        animation.setDuration(300);
        mLandmarkIcon.startAnimation(animation);

        mLandmarkName.setVisibility(View.VISIBLE);
        animation = new ScaleAnimation(0, 1, 1, 1);
        animation.setStartOffset(300);
        long width = mLandmarkName.getWidth();
        animation.setDuration(2*width);
        mLandmarkName.startAnimation(animation);


        mLandmarkName.setClickable(true);
        mLandmarkIcon.setClickable(true);

        if (isUploaded == false || isCanGetResult == false){
            mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.not_found));
            mLandmarkName.setClickable(false);
            mLandmarkIcon.setClickable(false);
            mLandmarkName.setText("Kết nối bị lỗi");
            return;
        }

        if (isFound == false){
            mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.not_found));
            mLandmarkName.setClickable(false);
            mLandmarkIcon.setClickable(false);
            mLandmarkName.setText("Không tìm thấy");
        }else{
            if (Setting.mMode == 1){
                mLandmarkName.setText(Setting.mLandmark.Name);
                switch(Integer.parseInt(Setting.mLandmark.Type)){
                    case 1:
                        mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.landmark));
                        break;
                    case 2:
                        mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.museum));
                        break;
                    case 3:
                        mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.education));
                        break;
                    case 4:
                        mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.amusement_park));
                        break;
                    case 5:
                        mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.religion));
                        break;
                    case 6:
                        mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.university));
                        break;
                    case 7:
                        mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.shopping));
                        break;
                    case 8:
                        mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.bar));
                        break;
                }
            }
            else
            {
                mLandmarkName.setText(Setting.mInfo.mField2);
                switch(Setting.mType){
                    case 1:
                        mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.book));
                        break;
                    case 2:
                        mLandmarkIcon.setBackground(activity.getResources().getDrawable(R.drawable.movie));
                        break;
                }
            }
        }
	}
	
	@Override
	protected void onPreExecute(){
        mMenu.setVisibility(View.INVISIBLE);
        mDownAnimView.setVisibility(View.VISIBLE);
        mDownAnimView.startAnimation(moveDown);
        finished = false;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		String url = params[0];

        isFound = false;
        isUploaded = true;
        isCanGetResult = true;

		File file = new File(url);

        int timeoutConnection = 3000;
        int timeoutSocket = 3000;

        HttpParams httpPara = new BasicHttpParams();
        HttpConnectionParams.setSoTimeout(httpPara, timeoutSocket);
        HttpConnectionParams.setConnectionTimeout(httpPara, timeoutConnection);

	    HttpClient client = new DefaultHttpClient(httpPara);
        HttpPost post = new HttpPost(Setting.uploadServer);

        FileBody bin = new FileBody(file);
        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("uploadedfile1", bin);
        post.setEntity(reqEntity);

        try{
            HttpResponse response = client.execute(post);
            HttpEntity resEntity = response.getEntity();
            String output = EntityUtils.toString(resEntity);
            if (output.contains("has been uploaded.") == false){
                isUploaded = false;
                return false;
            }
        }catch(Exception ex){
            isUploaded = false;
        	return false;
        }

        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);

        DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

        String imageName = url.substring(url.lastIndexOf('/')+1, url.length() - 4);
        String URL = "";

        if (Setting.mMode == 1)
            URL = Setting.detectServer + "/" + imageName;
        else
            URL = Setting.matchingServer + "/" + imageName;

        HttpGet httpGet = new HttpGet(URL);

        try{
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String output = EntityUtils.toString(httpEntity);

            if (Setting.mMode == 1){
                makeLandmark(output);
            }
            else{
                makeInfo(output);
            }
        }catch(Exception ex){
            isCanGetResult = false;

            return false;
        }
        return true;
	}

    public void makeInfo(String jsonString) throws JSONException {
        try{
            JSONObject json = new JSONObject(jsonString);
            String detect = json.getString("MatchingResult");
            JSONObject value = new JSONObject(detect);

            String f1 = value.getString("Field1");
            String f2 = value.getString("Field2");
            String f3 = value.getString("Field3");
            String f4 = value.getString("Field4");
            String f5 = value.getString("Field5");
            String f6 = value.getString("Field6");
            String f7 = value.getString("Field7");
            String f8 = value.getString("Field8");
            String f9 = value.getString("Field9");
            String f10 = value.getString("Field10");
            String f11 = value.getString("Field11");

            Setting.mType = Integer.parseInt(value.getString("Type"));

            JSONArray photosO = value.getJSONArray("Photos");
            JSONArray photos_descO = value.getJSONArray("Photos_Desc");
            JSONArray videosO = value.getJSONArray("Videos");
            JSONArray videos_desO = value.getJSONArray("Videos_Desc");
            JSONArray tiltes_O = value.getJSONArray("Title");
            JSONArray bodies_O = value.getJSONArray("Body");

            List<String> photos = new ArrayList<String>();
            List<String> photos_desc = new ArrayList<String>();
            List<String> videos = new ArrayList<String>();
            List<String> videos_desc = new ArrayList<String>();
            List<String> titles = new ArrayList<String>();
            List<String> bodies = new ArrayList<String>();

            for(int i = 0; i < photosO.length();i++)
                photos.add(photosO.getString(i));

            for(int i = 0; i < photos_descO.length();i++){
                if (photos_descO.getString(i) != "null")
                    photos_desc.add(photos_descO.getString(i));
                else
                    photos_desc.add("");
            }

            for(int i = 0; i < videosO.length();i++)
                videos.add(videosO.getString(i));

            for(int i = 0; i < videos_desO.length();i++){
                if (videos_desO.getString(i) != "null")
                    videos_desc.add(videos_desO.getString(i));
                else
                    videos_desc.add("");
            }

            for(int i = 0; i < tiltes_O.length();i++)
                titles.add(tiltes_O.getString(i));

            for(int i = 0; i < bodies_O.length();i++)
                bodies.add(bodies_O.getString(i));

            Setting.SetInfo(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11);
            Setting.SetInfoVideo(videos, videos_desc);
            Setting.SetInfoImage(photos, photos_desc);
            Setting.SetPreivew(titles, bodies);

            isFound = true;
        }catch (Exception ex){
            isFound = false;
        }
    }

    public void makeLandmark(String jsonString) throws JSONException {
        try{
            JSONObject json = new JSONObject(jsonString);
            String detect = json.getString("DetectResult");
            JSONObject value = new JSONObject(detect);
            String name = value.getString("Name");
            String address = value.getString("Address");
            String description = value.getString("Description");
            String phone = value.getString("Phone");

            JSONArray photosO = value.getJSONArray("Photos");
            JSONArray photos_descO = value.getJSONArray("Photos_Desc");
            JSONArray videosO = value.getJSONArray("Videos");
            JSONArray videos_desO = value.getJSONArray("Videos_Desc");

            List<String> photos = new ArrayList<String>();
            List<String> photos_desc = new ArrayList<String>();
            List<String> videos = new ArrayList<String>();
            List<String> videos_desc = new ArrayList<String>();

            for(int i = 0; i < photosO.length();i++)
                photos.add(photosO.getString(i));

            for(int i = 0; i < photos_descO.length();i++){
                if(photos_descO.getString(i) == "null")
                    photos_desc.add("");
                else
                    photos_desc.add(photos_descO.getString(i));
            }

            for(int i = 0; i < videosO.length();i++)
                videos.add(videosO.getString(i));

            for(int i = 0; i < videos_desO.length();i++){
                if (videos_desO.getString(i) == "null")
                    videos_desc.add("");
                else
                videos_desc.add(videos_desO.getString(i));
            }

            String wikiKey = value.getString("WikiKey");
            String type = value.getString("Type");
            String thumbURL = value.getString("ThumbUrl");

            Setting.SetInfoLandMark(name, address, description, phone, wikiKey, type, thumbURL);
            Setting.SetInfoVideo(videos, videos_desc);
            Setting.SetInfoImage(photos, photos_desc);

            isFound = true;
        }catch (Exception ex){
            isFound = false;
        }
    }
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}
}