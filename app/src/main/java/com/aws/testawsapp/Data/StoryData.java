package com.aws.testawsapp.Data;

import android.graphics.Bitmap;

import java.util.List;

import lombok.Data;

@Data
public class StoryData {
   private Bitmap story_profile;
   private String story_name;
   private Bitmap story_picture;
   private boolean like_check;
   private boolean collection_check;
   private List<CommentData> commentlist;
}
