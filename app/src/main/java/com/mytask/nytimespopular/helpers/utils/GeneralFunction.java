package com.mytask.nytimespopular.helpers.utils;


import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mytask.nytimespopular.R;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class GeneralFunction {

    public static void loadImage(Context mContext, String imgUrl, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_loading);
        requestOptions.error(R.color.gray);
        Glide.with(mContext).applyDefaultRequestOptions(requestOptions).load(imgUrl).placeholder(R.mipmap.ic_launcher_round).into(imageView);
    }

    public static MultipartBody.Part getImageMultipart(String path, String name) {
        File file = new File(path);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData(name, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        return filePart;
    }

    public static void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
