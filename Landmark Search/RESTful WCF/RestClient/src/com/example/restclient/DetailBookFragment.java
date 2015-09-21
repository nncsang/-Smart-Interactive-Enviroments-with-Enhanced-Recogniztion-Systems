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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by ChauSang on 6/24/13.
 */
public class DetailBookFragment extends Fragment {
    Activity mActivity = null;

    ImageView mBookCover = null;

    TextView mBookName = null;
    TextView mBookAuthor = null;
    TextView mBookWholeSaler = null;
    TextView mBookPublisher = null;
    TextView mBookWeight = null;
    TextView mBookPageAmount = null;
    TextView mBookDate = null;
    TextView mBookDescription = null;

    ImageButton mBookingButton = null;
    ImageButton mDownloadButton = null;
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
        View root = inflater.inflate(R.layout.book_detail, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBookCover = (ImageView) getView().findViewById(R.id.book_cover);

        mBookName = (TextView) getView().findViewById(R.id.book_name);
        mBookAuthor = (TextView) getView().findViewById(R.id.book_author);
        mBookPublisher = (TextView) getView().findViewById(R.id.book_publisher);
        mBookWholeSaler = (TextView) getView().findViewById(R.id.book_wholesaler);
        mBookDate = (TextView) getView().findViewById(R.id.book_publish_date);
        mBookDescription = (TextView) getView().findViewById(R.id.book_description);
        mBookWeight = (TextView) getView().findViewById(R.id.book_weight);
        mBookPageAmount = (TextView) getView().findViewById(R.id.book_pages);

        mBookingButton = (ImageButton)getView().findViewById(R.id.btn_book_purchase);
        mDownloadButton = (ImageButton)getView().findViewById(R.id.btn_download_ebook);
        mReviewButton = (ImageButton)getView().findViewById(R.id.btn_book_review);
        mSimilarButton = (ImageButton)getView().findViewById(R.id.btn_similar_book);

        mReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review();
            }
        });
        mBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booking();
            }
        });
        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download();
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

    private int RC = 5;

    public void SetBookInfor(){
        new StreamImageTask(mBookCover).execute(Setting.mInfo.mField1);
        mBookName.setText(Setting.mInfo.mField2);
        mBookAuthor.setText(Setting.mInfo.mField3);
        mBookPublisher.setText(Setting.mInfo.mField4);
        mBookWholeSaler.setText(Setting.mInfo.mField5);
        mBookWeight.setText(Setting.mInfo.mField6);
        mBookPageAmount.setText(Setting.mInfo.mField7);
        mBookDate.setText(Setting.mInfo.mField8);
        mBookDescription.setText(Setting.mInfo.mField9);
    }

    public void photo(){
        mActivity.startActivityForResult(mPhotoIntent, RC);
    }

    public void review(){
        mActivity.startActivityForResult(mReview, RC);
    }

    public void booking(){
        Intent browse = new Intent(Intent.ACTION_VIEW , Uri.parse(Setting.mInfo.mField10));
        mActivity.startActivityForResult(browse, RC);
    }

    public void download(){
        if (Setting.mInfo.mField11 == "null")
            return;
        Intent browse = new Intent(Intent.ACTION_VIEW , Uri.parse(Setting.mInfo.mField11));
        mActivity.startActivityForResult(browse, RC);
    }

    public void ReleaseBookInfor(){
        mBookCover.setImageBitmap(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.white_background));
    }
}