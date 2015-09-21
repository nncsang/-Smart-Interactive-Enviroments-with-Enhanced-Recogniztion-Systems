package com.example.restclient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ChauSang on 6/24/13.
 */
public class DetailLandmarkFragment extends Fragment {

    private Intent mCallIntent = null;
    private Intent mDirectionIntent = null;
    private Intent mVideoIntent = null;
    private Intent mPhotoIntent = null;

    private ImageButton mCall = null;
    private ImageButton mDirection = null;
    private ImageButton mVideo = null;
    private ImageButton mPhoto = null;

    private ImageView mThumbnail = null;

    private Activity mActivity = null;

    private WebView mWebView = null;
    private AsyncHttpClient mAsyncHttpClient = null;
    private String mURL = null;

    private static TextView mLandmarkName = null;
    private static TextView mLandmarkAddress = null;
    private static TextView mLandmarkDescription = null;

    private class StreamImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView imgView;

        public StreamImageTask(ImageView imgView){
            this.imgView = imgView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;
            try{
                InputStream inputStream = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }catch (Exception e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result){
            imgView.setImageBitmap(result);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.landmark_detail, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCall = (ImageButton)getView().findViewById(R.id.btn_call);
        mDirection = (ImageButton) getView().findViewById(R.id.btn_map);
        mVideo = (ImageButton) getView().findViewById(R.id.btn_video);
        mPhoto = (ImageButton) getView().findViewById(R.id.btn_photos);

        mThumbnail = (ImageView)getView().findViewById(R.id.landmark_thumbnail);

        mWebView = (WebView) getView().findViewById(R.id.landmark_webview);

        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call("01626161879");
            }
        });
        mDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video();
            }
        });

        mPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photo();
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
        }});

        mLandmarkName = (TextView) getView().findViewById(R.id.landmark_name);
        mLandmarkDescription = (TextView) getView().findViewById(R.id.landmark_description);
        mLandmarkAddress = (TextView) getView().findViewById(R.id.landmark_address);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC){
        }
    }

    private int RC = 4;
    public void call(String phoneNum){
        mCallIntent = new Intent(Intent.ACTION_CALL);
        mCallIntent.setData(Uri.parse("tel:" + phoneNum));
        mActivity.startActivityForResult(mCallIntent, RC);

    }
/*
    public void web(String url){
        mWebrowserIntent = new Intent(Intent.ACTION_VIEW);
        mWebrowserIntent.setData(Uri.parse(url));
        mActivity.startActivityForResult(mWebrowserIntent, RC);
    }*/

    public void direction(){
        mDirectionIntent = new Intent(Intent.ACTION_VIEW,
        Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
        mActivity.startActivityForResult(mDirectionIntent, RC);
    }

    public void photo(){
        mPhotoIntent = new Intent(mActivity, ImageViewerActivity.class);
        mActivity.startActivityForResult(mPhotoIntent, RC);
    }

    public void video(){
        mVideoIntent = new Intent(mActivity, VideoViewerActivity.class);
        mActivity.startActivityForResult(mVideoIntent, RC);
    }

    public void SetLandmarkInfor(){
        mLandmarkName.setText(Setting.mLandmark.Name);
        mLandmarkAddress.setText(Setting.mLandmark.Address);
        mLandmarkDescription.setText(Setting.mLandmark.Description);
        new StreamImageTask(mThumbnail).execute(Setting.mLandmark.ThumbUrl);

        if (!Setting.mLandmark.WikiKey.contains("http"))
            mURL = "https://vi.wikipedia.org/wiki/" + Setting.mLandmark.WikiKey;
        else
            mURL = Setting.mLandmark.WikiKey;

        mAsyncHttpClient = new AsyncHttpClient();
        mAsyncHttpClient.get(mURL, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response) {
                mWebView.loadUrl(mURL);
            }
        });
    }

    public void ReleaseLandmarkInfor(){
        mThumbnail.setImageBitmap(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.white_background));
        mWebView.loadUrl("about:blank");
    }

}