package com.example.restclient;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ChauSang on 6/24/13.
 */


public class GalleryActivity extends FragmentActivity implements GalleryListener{
    private ViewAnimator mViewAnimator;

    private boolean mIsDetail = false;

    private DetailLandmarkFragment mDetailLandmarkFragment = null;
    private DetailBookFragment mDetailBookFragment = null;
    private DetailPosterFragment mDetailPosterFragment = null;
    private GalleryFragment mGalleryFragment = null;

    int mLastFragemnt = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery);

        mViewAnimator = (ViewAnimator) findViewById(R.id.viewFlipper);

        mDetailLandmarkFragment = (DetailLandmarkFragment)getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        mDetailBookFragment = (DetailBookFragment)getSupportFragmentManager().findFragmentById(R.id.bookFragment);
        mDetailPosterFragment = (DetailPosterFragment)getSupportFragmentManager().findFragmentById(R.id.posterFragment);
        mGalleryFragment = (GalleryFragment)getSupportFragmentManager().findFragmentById(R.id.galaxyFragment);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case 4:
                mDetailLandmarkFragment.onActivityResult(requestCode, resultCode, data);
                break;
            case 5:
                mDetailBookFragment.onActivityResult(requestCode, resultCode, data);
                break;
            case 6:
                mDetailPosterFragment.onActivityResult(requestCode, resultCode, data);
                break;
            default:
                mGalleryFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void filp() {
        if (Setting.mMode == 1){
            AnimationFactory.flipTransition(mViewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT, 0);
            mDetailLandmarkFragment.SetLandmarkInfor();
            mLastFragemnt = 0;
        }
        else
        {
            if (Setting.mType == 1)
            {
                AnimationFactory.flipTransition(mViewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT, 1);
                mDetailBookFragment.SetBookInfor();
                mLastFragemnt = 1;
            }
            else{
                AnimationFactory.flipTransition(mViewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT, 2);
                mDetailPosterFragment.SetPosterInfor();
                mLastFragemnt = 2;
            }
        }

        mIsDetail = true;
    }

    @Override
    public void onBackPressed() {
        final Fragment fragment = (Fragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        if (mIsDetail == true){
            AnimationFactory.flipTransition(mViewAnimator, AnimationFactory.FlipDirection.RIGHT_LEFT, 3);
            mIsDetail = false;
        }else{
            mGalleryFragment.deleteOldPhoto();
            super.onBackPressed();
        }
    }

    @Override
    public void releaseInfor(){
        if (mLastFragemnt == -1)
            return;

        switch (mLastFragemnt){
            case 0:
                mDetailLandmarkFragment.ReleaseLandmarkInfor();
                break;
            case 1:
                mDetailBookFragment.ReleaseBookInfor();
                break;
            case 2:
                mDetailPosterFragment.ReleasePosterInfor();
                break;
        }

        mLastFragemnt = -1;
    }
}