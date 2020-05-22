package com.mytask.nytimespopular.helpers.interfaces;

import android.content.Intent;

public interface ActivityResultCallBack {
    void callBack(int requestCode, int resultCode, Intent data);
}
