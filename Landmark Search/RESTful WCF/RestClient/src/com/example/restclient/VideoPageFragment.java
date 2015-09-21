package com.example.restclient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by PC on 7/4/13.
 */
public class VideoPageFragment extends ImagePageFragment {
    private class StreamVideoThumbnailTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView imgView;
        private ImageButton btnPlay;
        private String videoUrl;

        public StreamVideoThumbnailTask(ImageView imgView, ImageButton btnPlay){
            this.imgView = imgView;
            this.btnPlay = btnPlay;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String videoId = urls[0];
            Bitmap bitmap = null;
            try{
                String thumbnailUrl = "http://img.youtube.com/vi/"+ videoId + "/0.jpg";
                videoUrl = "http://www.youtube.com/watch?v=" + videoId;
                InputStream inputStream = new URL(thumbnailUrl).openStream();
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
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)));
                }
            });
            btnPlay.setVisibility(View.VISIBLE);
        }
    }

    public static VideoPageFragment create(int pageNumber) {
        VideoPageFragment fragment = new VideoPageFragment();
        Bundle args = new Bundle();
        args.putInt(ImagePageFragment.ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public VideoPageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_image_page, container, false);
        new StreamVideoThumbnailTask((ImageView)rootView.findViewById(R.id.fragment_image_view),
                (ImageButton)rootView.findViewById(R.id.btnPlay)).execute(imgUrls.get(mPageNumber));
        TextView textView = (TextView)rootView.findViewById(R.id.fragment_text_view);
        textView.setText(imgTexts.get(mPageNumber));
        //textView.startAnimation(moveUp);
        textView.startAnimation(moveDown);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    TextView txtView = (TextView)view.findViewById(R.id.fragment_text_view);
                    //txtView.startAnimation(moveUp);
                    txtView.startAnimation(moveDown);
                    return true;
                }
                return false;
            }
        });
        return rootView;
    }
}
