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
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by ChauSang on 6/24/13.
 */
public class GalleryFragment extends Fragment {

    private static final int CAMERA_REQUEST_CODE = 4444;
    private static final int GALAXY_REQUEST_CODE = 5555;

    private ImageView mImageViewer = null;

    private ImageButton mGalleryButton = null;
    private ImageButton mCameraButton = null;
    private ImageButton mSettingButton = null;
    private ImageButton mDetectButton = null;

    private ImageButton mLandmarkIcon = null;
    private TextView mLandmarkName = null;

    private Activity mActivity = null;

    private Intent cameraIntent = null;
    private Intent photoPickerIntent = null;
    private Intent settingIntent;

    private ImageView upAnimViewer = null;
    private ImageView downAnimViewer = null;
    private LinearLayout menu = null;

    private Uri outputFileUri = null;
    private static Uri oldFileUri = null;

    private int targetH = 0;
    private int targetW = 0;

    private GalleryListener mGalleryListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mGalleryListener = (GalleryListener)activity;
        mActivity = activity;

        String filename = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + ".jpg";
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + filename;
        File file = new File(path);
        outputFileUri = Uri.fromFile(file);

        cameraIntent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoPickerIntent  = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.search_screen, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        targetH = mActivity.getWindowManager().getDefaultDisplay().getHeight();
        targetW = mActivity.getWindowManager().getDefaultDisplay().getWidth();

        mImageViewer = (ImageView)getView().findViewById(R.id.imageView);
        upAnimViewer = (ImageView)getView().findViewById(R.id.upAnimationView);
        downAnimViewer = (ImageView)getView().findViewById(R.id.downAnimationView);
        menu = (LinearLayout)getView().findViewById(R.id.searchScreenMenu);

        mGalleryButton = (ImageButton)getView().findViewById(R.id.gallery_search);
        mCameraButton = (ImageButton)getView().findViewById(R.id.camera_search);
        mSettingButton = (ImageButton)getView().findViewById(R.id.setting_search);
        mDetectButton = (ImageButton)getView().findViewById(R.id.detect_search);

        mLandmarkIcon = (ImageButton)getView().findViewById(R.id.landmarkIcon);
        mLandmarkName = (TextView)getView().findViewById(R.id.landmarkName);

        mLandmarkName.setVisibility(View.INVISIBLE);
        mLandmarkIcon.setVisibility(View.INVISIBLE);

        settingIntent = new Intent(mActivity, SettingsActivity.class);

        mGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLandmarkName.setVisibility(View.INVISIBLE);
                mLandmarkIcon.setVisibility(View.INVISIBLE);

                mActivity.startActivityForResult(photoPickerIntent, GALAXY_REQUEST_CODE);
            }
        });

        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLandmarkName.setVisibility(View.INVISIBLE);
                mLandmarkIcon.setVisibility(View.INVISIBLE);

                String filename = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + ".jpg";
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + filename;
                File file = new File(path);
                outputFileUri = Uri.fromFile(file);

                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                mActivity.startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }
        });

        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.startActivityForResult(settingIntent, 5);
            }
        });

        mDetectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLandmarkName.setVisibility(View.INVISIBLE);
                mLandmarkIcon.setVisibility(View.INVISIBLE);

                new HttpPostAsync(mActivity, upAnimViewer, downAnimViewer, menu, mLandmarkIcon, mLandmarkName).execute(outputFileUri.getPath());
            }
        });

        mLandmarkIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGalleryListener.filp();
            }
        });
        mLandmarkName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGalleryListener.filp();
            }
        });

        mActivity.startActivityForResult(photoPickerIntent, GALAXY_REQUEST_CODE);
    }

    private void setPhoto()
    {
        mGalleryListener.releaseInfor();

        String imgPath = outputFileUri.getPath();
        BitmapFactory.Options bmOpt = new BitmapFactory.Options();
        bmOpt.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imgPath, bmOpt);

        int photoW = bmOpt.outWidth;
        int photoH = bmOpt.outHeight;
        float aspectRatio = (float) photoW / (float) photoH;

        int scale = 1;
        if ((targetW > 0) || (targetH > 0)){
            scale = Math.min(photoW / targetW, photoH / targetH);
        }

        bmOpt.inJustDecodeBounds = false;
        bmOpt.inSampleSize = scale;
        bmOpt.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, bmOpt);
        try{
           mImageViewer.setImageBitmap(RotateBitmap(bitmap, 90));
        }catch (Exception ex){
        }

        int newWidth = 640;
        int newHeight = (int) (newWidth / aspectRatio);

        String filename = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + ".jpg";
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + filename;
        File file = new File(path);

        try {
            FileOutputStream out = new FileOutputStream(file);
            Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true).compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            outputFileUri = Uri.fromFile(file);
            oldFileUri = Uri.fromFile(file);
        } catch (Exception e) {

        }
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != mActivity.RESULT_OK)
            return;

        switch(requestCode){
            case CAMERA_REQUEST_CODE:
                deleteOldPhoto();
                setPhoto();
                break;
            case GALAXY_REQUEST_CODE:
                File file = new File(getRealPathFromURI(data.getData()));
                outputFileUri = Uri.fromFile(file);
                deleteOldPhoto();
                setPhoto();
                break;
        }
    }

    public static void deleteOldPhoto(){
        if (oldFileUri == null)
            return;

        File file = new File(oldFileUri.getPath());
        file.delete();
    }

    public String getRealPathFromURI(Uri contentUri) {
        String [] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = mActivity.managedQuery( contentUri, proj, null, null,null);

        if (cursor == null)
            return null;

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}