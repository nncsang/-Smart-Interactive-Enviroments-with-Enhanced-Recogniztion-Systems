package com.example.restclient;

import android.content.Intent;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.cert.CertificateNotYetValidException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends FragmentActivity  {
    private Intent mCamera = null;
    private Intent mGallery = null;
    private Intent mAbout = null;
    private Intent mSetting = null;

    private ImageButton mGalleryButton = null;
    private ImageButton mCameraButton = null;
    private ImageButton mSettingButton = null;
    private ImageButton mAboutButton = null;
    private ImageButton mExitButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_screen);

        mCamera = new Intent(this, CameraActivity.class);
        mGallery = new Intent(this, GalleryActivity.class);
        mAbout = new Intent(this, AboutActivity.class);
        mSetting = new Intent(this, SettingsActivity.class);

        mGalleryButton = (ImageButton)findViewById(R.id.galleryButton);
        mCameraButton = (ImageButton)findViewById(R.id.cameraButton);
        mSettingButton = (ImageButton)findViewById(R.id.settingButton);
        mAboutButton = (ImageButton)findViewById(R.id.aboutButton);
        mExitButton = (ImageButton)findViewById(R.id.exitButton);

        mGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mGallery);
            }
        });
        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mCamera);
            }
        });
        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mSetting);
            }
        });
        mAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mAbout);
            }
        });
        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
	}
}
