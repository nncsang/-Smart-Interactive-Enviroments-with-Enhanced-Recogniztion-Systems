package com.example.restclient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by ChauSang on 6/24/13.
 */
public class DetailPosterFragment extends Fragment {
    Activity mActivity = null;

    ImageView mPosterCover = null;

    TextView mPosterName = null;
    TextView mPosterYear = null;
    TextView mPosterCatogory = null;
    TextView mPosterDirector = null;
    TextView mPosterWriter= null;
    TextView mPosterActor = null;
    TextView mPosterRating = null;
    TextView mPosterDescription = null;

    ImageButton mBookingButton = null;
    ImageButton mTrailerButton = null;
    ImageButton mReviewButton = null;
    ImageButton mSimilarButton = null;

    private Intent mPhotoIntent = null;
    private Intent mVideoIntent = null;
    private Intent mReview = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.movie_detail, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPosterCover = (ImageView) getView().findViewById(R.id.movie_poster);

        mPosterName = (TextView) getView().findViewById(R.id.movie_name);
        mPosterYear = (TextView) getView().findViewById(R.id.movie_year);
        mPosterCatogory = (TextView) getView().findViewById(R.id.movie_category);
        mPosterDirector = (TextView) getView().findViewById(R.id.movie_director);
        mPosterWriter = (TextView) getView().findViewById(R.id.movie_writer);
        mPosterActor = (TextView) getView().findViewById(R.id.movie_actor);
        mPosterRating = (TextView) getView().findViewById(R.id.movie_rating);
        mPosterDescription = (TextView) getView().findViewById(R.id.movie_description);

        mBookingButton = (ImageButton)getView().findViewById(R.id.btn_ticket_purchase);
        mTrailerButton = (ImageButton)getView().findViewById(R.id.btn_view_trailer);
        mReviewButton = (ImageButton)getView().findViewById(R.id.btn_movie_review);
        mSimilarButton = (ImageButton)getView().findViewById(R.id.btn_movie_photo);

        mBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booking();
            }
        });
        mReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review();
            }
        });
        mTrailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video();
            }
        });
        mSimilarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photo();
            }
        });

        mPhotoIntent = new Intent(mActivity, ImageViewerActivity.class);
        mReview = new Intent(mActivity, ReviewActivity.class);
        mVideoIntent = new Intent(mActivity, VideoViewerActivity.class);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC){
        }
    }

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

    private int RC = 6;

    public void SetPosterInfor(){
        new StreamImageTask(mPosterCover).execute(Setting.mInfo.mField1);
        mPosterName.setText(Setting.mInfo.mField2);
        mPosterYear.setText(Setting.mInfo.mField3);
        mPosterCatogory.setText(Setting.mInfo.mField4);
        mPosterDirector.setText(Setting.mInfo.mField5);
        mPosterWriter.setText(Setting.mInfo.mField6);
        mPosterActor.setText(Setting.mInfo.mField7);
        mPosterRating.setText(Setting.mInfo.mField8);
        mPosterDescription.setText(Setting.mInfo.mField9);
    }

    public void photo(){
        mActivity.startActivityForResult(mPhotoIntent, RC);
    }

    public void review(){
        mActivity.startActivityForResult(mReview, RC);
    }

    public void video(){

        mActivity.startActivityForResult(mVideoIntent, RC);
    }

    public void booking(){
        Intent browse = new Intent(Intent.ACTION_VIEW , Uri.parse(Setting.mInfo.mField10));
        mActivity.startActivityForResult(browse, RC);
    }

    public void ReleasePosterInfor(){
        mPosterCover.setImageBitmap(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.white_background));
    }
}