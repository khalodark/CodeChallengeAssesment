package com.mytask.nytimespopular.helpers.interfaces;

import android.content.Intent;

/**
 * Interface for Intercepting the Result call back (@Method ActivityForResult)
 * inside the fragment
 */
public interface ActivityResultCallBack {
    void callBack(int requestCode, int resultCode, Intent data);
}
