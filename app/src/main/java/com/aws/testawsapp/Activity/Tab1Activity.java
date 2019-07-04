package com.aws.testawsapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.aws.testawsapp.Adapter.StoryAdapter;
import com.aws.testawsapp.Data.CommentData;
import com.aws.testawsapp.Data.StoryData;
import com.aws.testawsapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tab1Activity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rl_story;
    public String path;
    ImageButton imbtn_camera;
    private String imageFilePath;
    private Uri photoUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1_activity);


        component();
    }
    public void component(){
        imbtn_camera=(ImageButton)findViewById(R.id.imbtn_camera);
        imbtn_camera.setOnClickListener(this);
        path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/test.jpg";
        String path3= Environment.getExternalStorageDirectory().getAbsolutePath()+"/ffd.jpg";
        String path2= Environment.getExternalStorageDirectory().getAbsolutePath()+"/FitnessGirl0.jpg";
        File f=new File(path);
        File f2=new File(path2);
        File f3=new File(path3);
        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        Bitmap bitmap2= BitmapFactory.decodeFile(f2.getAbsolutePath());
        Bitmap bitmap3= BitmapFactory.decodeFile(f3.getAbsolutePath());



        List<CommentData> cdata=new ArrayList<>();
       for(int i=0; i<10; i++) {
           CommentData cda = new CommentData();
           cda.setComment_name("박석원");
           cda.setComment_text("이거뭐냐");
           cda.setComment_profile(bitmap);
           cdata.add(cda);
       }
        List<StoryData> sdatalist= new ArrayList<>();
        StoryData sd=new StoryData();
        sd.setStory_name("김승영");
        sd.setLike_check(true);
        sd.setCollection_check(false);
        sd.setCommentlist(cdata);
        sd.setStory_profile(bitmap3);
        sd.setStory_picture(bitmap2);
        sdatalist.add(sd);
        StoryAdapter sadapter=new StoryAdapter(sdatalist,this);
        rl_story=(RecyclerView)findViewById(R.id.rl_story);
        rl_story.setAdapter(sadapter);
        rl_story.setLayoutManager(new LinearLayoutManager(this));
    }
    public Bitmap getBitmap(Uri uri){
        Bitmap bitmap = null;
        try {
            File f= new File(uri.getPath());
            boolean d=f.exists();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imbtn_camera:
                sendTakePhotoIntent();
                break;
        }
    }
    private void sendTakePhotoIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }

            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this, getPackageName(), photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, 100);
            }
        }
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            //     Bundle extras = data.getExtras();

            Bitmap imageBitmap = BitmapFactory.decodeFile(imageFilePath);
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

            Bitmap resultbit = rotate(imageBitmap, exifDegree);
            try {
                FileOutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.jpg");
                resultbit.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.close();
            } catch (Exception e) {

            }

        }
    }
}
