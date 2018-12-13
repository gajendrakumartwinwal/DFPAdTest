package com.dfp.test.utils;

import android.content.Context;

import com.google.android.gms.ads.AdSize;

public class AdSizeUtil {
    public static AdSize[] getMrecSize() {
        AdSize[] result = new AdSize[1];
        AdSize size1 = new AdSize(300, 250);
        result[0] = size1;
        return result;
    }

    public static AdSize[] getBannerAdSize() {
        return new AdSize[]{AdSize.BANNER};
    }
}
