package com.example.restclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.*;
import android.provider.Settings;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by ChauSang on 6/26/13.
 */
public class SettingsActivity extends Activity {

//    private Switch mSwitchGPS = null;
//    private GPSTracker mGPSTracker = null;
    private RadioButton mTemplateMode = null;
    private RadioButton mClassifyMode = null;
    private Context mActivity;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_settings);

        mActivity = this;
        mTemplateMode = (RadioButton)findViewById(R.id.radioBtn_template_matching);
        mClassifyMode = (RadioButton)findViewById(R.id.radioBtn_image_classification);

        if (Setting.mMode == 1){
            mClassifyMode.setChecked(true);
            mTemplateMode.setChecked(false);
        }else{
            mClassifyMode.setChecked(false);
            mTemplateMode.setChecked(true);
        }

        mTemplateMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true){
                    Setting.mMode = 2;
                    mTemplateMode.setChecked(true);
                    mClassifyMode.setChecked(false);
                }else{
                    mTemplateMode.setChecked(false);
                }
            }
        });

        mClassifyMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true){
                    Setting.mMode = 1;
                    mTemplateMode.setChecked(false);
                    mClassifyMode.setChecked(true);

                }else{
                    mClassifyMode.setChecked(false);
                }
            }
        });

//        mSwitchGPS = (Switch)findViewById(R.id.switch_enable_gps);
//
//        mGPSTracker = new GPSTracker(this);
//        if(mGPSTracker.canGetLocation()){
//            mSwitchGPS.setChecked(true);
//        }else{
//            mSwitchGPS.setChecked(false);
//        }
//
//        mSwitchGPS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                compoundButton.setChecked(true);
////                if (b == true)
////                    mGPSTracker.showSettingsAlert();
////                else{
////                    turnGPSOff();
////                }
////                if (mGPSTracker.canGetLocation())
////                    mSwitchGPS.setChecked(true);
////                else
////                    mSwitchGPS.setChecked(false);
//            }
//        });
    }

    private void turnGPSOn(){
//        String provider = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
//        if(!provider.contains("gps")){
//            final Intent intent = new Intent();
//            intent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
//            intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
//            intent.setData(Uri.parse("3"));
//            sendBroadcast(intent);
//        }
    }
    // If GPS is ON
    private void turnGPSOff(){
//        String provider = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
//        if(provider.contains("gps")){
//            final Intent intent = new Intent();
//            intent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
//            intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
//            intent.setData(Uri.parse("3"));
//            sendBroadcast(intent);
//        }
    }
}