package com.example.restclient;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ChauSang on 7/12/13.
 */
public class ReviewActivity extends Activity {
    TextView mTitleTextView = null;
    TextView mBodyTextView = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.review);

        LinearLayout root = (LinearLayout)findViewById(R.id.root);

        mTitleTextView = (TextView)findViewById(R.id.review_title);
        mBodyTextView = (TextView)findViewById(R.id.review_body);

        mTitleTextView.setVisibility(View.INVISIBLE);
        mBodyTextView.setVisibility(View.INVISIBLE);

        Review review = Setting.mReview;
        for(int i = 0; i < review.mTitle.size(); i++){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 15, 0, 0);

            LinearLayout reviewView = new LinearLayout(this);
            reviewView.setOrientation(LinearLayout.VERTICAL);
            reviewView.setPadding(25, 25, 25, 25);
            reviewView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            reviewView.setLayoutParams(layoutParams);
            reviewView.setBackground(getResources().getDrawable(R.drawable.blue_frame));

            TextView title = new TextView(this);
            mTitleTextView.getLayoutParams();
            title.setTextSize(19);
            title.setTextColor(Color.rgb(252, 0, 0));
            title.setTypeface(null, Typeface.BOLD);
            title.setLayoutParams(mTitleTextView.getLayoutParams());

            TextView body = new TextView(this);
            body.setTextSize(16);
            body.setTextColor(Color.BLACK);
            body.setLayoutParams(mBodyTextView.getLayoutParams());

            title.invalidate();
            body.invalidate();

            title.setText(review.mTitle.get(i));
            body.setText(review.mBody.get(i));

            reviewView.addView(title);
            reviewView.addView(body);
            root.addView(reviewView);
        }
    }
}