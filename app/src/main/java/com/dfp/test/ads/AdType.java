package com.dfp.test.ads;

/**
 * Created by Gajendra on 17/10/17.
 */

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.dfp.test.ads.AdType.FOOTER;
import static com.dfp.test.ads.AdType.HEADER;
import static com.dfp.test.ads.AdType.MREC;

/**
 * Define intDef for adtypes
 */
@IntDef({HEADER, FOOTER, MREC})
@Retention(RetentionPolicy.SOURCE)
public @interface AdType {
    int HEADER = 1;
    int FOOTER = 2;
    int MREC = 3;
}

