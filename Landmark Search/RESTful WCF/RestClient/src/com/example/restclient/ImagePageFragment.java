package com.example.restclient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by PC on 6/17/13.
 */
public class ImagePageFragment extends Fragment {

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

    public static final String ARG_PAGE = "page";
    protected int mPageNumber;
    protected static ArrayList<String> imgUrls = null;
    protected static ArrayList<String> imgTexts = null;
    protected static final long ANIMATION_DURATION = 700;
    protected static final long DELAY = 5000;
    protected static Animation moveUp;
    protected static Animation moveDown;

    public static void init(ArrayList<String> urls,
                            ArrayList<String> texts, int displayHeight){
        imgUrls = urls;
        imgTexts = texts;
        moveUp = new TranslateAnimation(0, 0, displayHeight, 0);
        moveDown = new TranslateAnimation(0, 0, 0, displayHeight);
        moveUp.setDuration(ANIMATION_DURATION);
        moveDown.setDuration(ANIMATION_DURATION);
        moveDown.setStartOffset(DELAY);
        moveDown.setFillEnabled(true);
        moveDown.setFillAfter(true);
    }

    public static ImagePageFragment create(int pageNumber) {
        ImagePageFragment fragment = new ImagePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ImagePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_image_page, container, false);
        new StreamImageTask((ImageView)rootView.findViewById(
                R.id.fragment_image_view)).execute(imgUrls.get(mPageNumber));
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

    public static int getPageCount(){
        return imgUrls.size();
    }

    public int getPageNumber() {
        return mPageNumber;
    }
}
