package com.aws.testawsapp.Activity;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.hardware.Camera;
import com.aws.testawsapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener, Camera.AutoFocusCallback {
    SurfaceView sv_camera_view;
    SurfaceHolder sv_camera_holder;
    Camera mcamera;
    ImageButton imgbtn_camera;
    String imageFilePath;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_acitivity);

        component();
    }

    public void component() {

        sv_camera_view = (SurfaceView) findViewById(R.id.sv_camera_view);
        imgbtn_camera = (ImageButton) findViewById(R.id.imbtn_mcamera);
        imgbtn_camera.setOnClickListener(this);
        mcamera = Camera.open();


        sv_camera_holder = sv_camera_view.getHolder();
        sv_camera_holder.addCallback(this);
        sv_camera_holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // 카메라 객체를 사용할 수 있게 연결한다.
            if(mcamera  == null){
                mcamera  = Camera.open();
            }

            // 카메라 설정
            Camera.Parameters parameters = mcamera .getParameters();

   /*         // 카메라의 회전이 가로/세로일때 화면을 설정한다.
            if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                parameters.set("orientation", "portrait");
                mcamera.setDisplayOrientation(90);
                parameters.setRotation(90);
            } else {
                parameters.set("orientation", "landscape");
                mcamera.setDisplayOrientation(0);
                parameters.setRotation(0);
            }
            mcamera.setParameters(parameters);*/

            mcamera.setPreviewDisplay(sv_camera_holder);

            // 카메라 미리보기를 시작한다.
            mcamera.startPreview();

            // 자동포커스 설정
            mcamera.autoFocus(this);
        } catch (IOException e) {
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // 카메라 화면을 회전 할 때의 처리
        if (holder.getSurface() == null){
            // 프리뷰가 존재하지 않을때
            return;
        }
        // 프리뷰를 다시 설정한다.
        try {
            mcamera .stopPreview();

            Camera.Parameters parameters = mcamera .getParameters();

            // 화면 회전시 사진 회전 속성을 맞추기 위해 설정한다.
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            if (rotation == Surface.ROTATION_0) {
                mcamera .setDisplayOrientation(90);
                parameters.setRotation(90);
            }else if(rotation == Surface.ROTATION_90){
                mcamera .setDisplayOrientation(0);
                parameters.setRotation(0);
            }else if(rotation == Surface.ROTATION_180){
                mcamera .setDisplayOrientation(270);
                parameters.setRotation(270);
            }else{
                mcamera .setDisplayOrientation(180);
                parameters.setRotation(180);
            }

            // 변경된 화면 넓이를 설정한다.
          //  parameters.setPreviewSize(previewSize.width, previewSize.height);
            mcamera .setParameters(parameters);

            // 새로 변경된 설정으로 프리뷰를 시작한다
            mcamera .setPreviewDisplay(sv_camera_holder);
            mcamera .startPreview();
            mcamera.autoFocus(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mcamera != null) {
            mcamera.stopPreview();
            mcamera.release();
            mcamera = null;
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imbtn_mcamera:
                mcamera.takePicture(null, null, pcall);
                break;
        }

    }

    @Override
    public void onAutoFocus(boolean success, Camera camera) {

    }

    Camera.PictureCallback pcall = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                Bitmap bitmap = BitmapFactory.decodeByteArray( data, 0, data.length ) ;

                File pictureFile = getOutputMediaFile();
                if (pictureFile == null) {
                    return;
                }

                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();

              Uri pictureUri = FileProvider.getUriForFile(getApplicationContext(), getPackageName(), pictureFile);





                    Intent i = new Intent(getApplicationContext(),new ResultCameraActivity().getClass());
                    i.putExtra("image",pictureUri);
                    startActivity(i);

            } catch (Exception e) {
                e.getMessage();
            }


        }
    };
    public File getOutputMediaFile () throws IOException {
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
}

