package com.aws.testawsapp.Data;

import android.graphics.Bitmap;

import lombok.Data;

@Data
public class CommentData {
    Bitmap comment_profile;
    String comment_name;
    String comment_text;
}
