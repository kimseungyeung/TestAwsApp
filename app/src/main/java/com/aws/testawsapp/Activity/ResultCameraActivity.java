package com.aws.testawsapp.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.aws.testawsapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultCameraActivity extends AppCompatActivity implements View.OnClickListener {
    Bitmap resultpicture;
    ImageView iv_result_picture;
    String imageFilePath;
    boolean checksave;
    Button btn_save, btn_edit, btn_cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_camera_activity);
        component();


    }

    public void component() {
        checksave=false;
        Intent i = getIntent();
        String path = i.getStringExtra("image");
        File f = new File(path);
        imageFilePath = f.getAbsolutePath();

        ExifInterface exif = null;

        try {
            exif = new ExifInterface(imageFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int exifOrientation;
        int exifDegree;

        if (exif != null) {
            exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            exifDegree = exifOrientationToDegrees(exifOrientation);
        } else {
            exifDegree = 0;
        }
        // Bitmap bit =getBitmap(uri);
        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        resultpicture = rotate(bitmap, exifDegree);
        iv_result_picture = (ImageView) findViewById(R.id.iv_result);
        iv_result_picture.setImageBitmap(resultpicture);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_save.setOnClickListener(this);
        btn_edit.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

    }

    public Bitmap getBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            File f = new File(uri.getPath());
            boolean d = f.exists();
            bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bitmap;
    }

    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap rotate(Bitmap src, float degree) {

// Matrix 객체 생성
        Matrix matrix = new Matrix();
// 회전 각도 셋팅
        matrix.postRotate(degree);
// 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), matrix, true);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,      /* prefix */
                ".jpg",         /* suffix */
                storageDir          /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

            if (!checksave) {
            File f=new File(imageFilePath);
            f.delete();
            }else{
                try {
                    File f=new File(imageFilePath);
                    f.delete();
                    FileOutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+f.getName());
                    resultpicture.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.close();
                } catch (Exception e) {

                }
            }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                checksave = true;
              finish();
                break;
            case R.id.btn_edit:
                break;
            case R.id.btn_cancel:
                checksave = false;
               finish();
                break;
        }
    }
}
