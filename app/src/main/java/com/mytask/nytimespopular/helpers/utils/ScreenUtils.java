package com.mytask.nytimespopular.helpers.utils;

import android.content.Context;

public final class ScreenUtils {

    private ScreenUtils() {
        // This class is not publicly instantiable
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
