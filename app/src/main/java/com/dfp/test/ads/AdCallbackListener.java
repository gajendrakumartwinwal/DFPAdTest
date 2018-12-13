package com.dfp.test.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

public interface AdCallbackListener {
    void AdLoaded(PublisherAdView view);

    void AdFailed(int errorCode);
}