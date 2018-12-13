package com.dfp.test.ads;

import com.dfp.test.utils.AdSizeUtil;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

/**
 * Created by Gajendra on 17/10/17.
 */

public class AdRequest {
    private String adUnitId;
    private int adType;


    private PublisherAdView publisherAdView;
    private AdCallbackListener adListener;

    private AdRequest(AdRequestBuilder builder) {
        this.adUnitId = builder.adUnitId;
        this.adType = builder.adType;
        this.publisherAdView = builder.publisherAdView;

        //Ready publisher adview to load an ad
        setPublisherAdViewDetails();

        this.adListener = builder.adListener;
    }

    /**
     * Set unit id and size to the publisher adview
     */
    private void setPublisherAdViewDetails() {
        publisherAdView.setAdUnitId(getAdUnitId());
        publisherAdView.setAdSizes(getAdSize());
    }

    public String getAdUnitId() {
        return adUnitId;
    }

    /**
     * Unique id for the AD to capture user timing and other
     */
    public String getUniqueID() {
        return hashCode() + "";
    }

    public int getAdType() {
        return adType;
    }

    public PublisherAdView getPublisherAdView() {
        return publisherAdView;
    }

    public AdSize[] getAdSize() {
        switch (getAdType()) {
            case AdType.HEADER:
                return AdSizeUtil.getBannerAdSize();
            case AdType.FOOTER:
                return AdSizeUtil.getBannerAdSize();
            case AdType.MREC:
                return AdSizeUtil.getMrecSize();
        }
        return null;
    }


    public AdCallbackListener getAdListener() {
        return adListener;
    }

    //Builder Class
    public static class AdRequestBuilder {

        // required parameters
        private String adUnitId;
        private int adType;
        private PublisherAdView publisherAdView;

        private AdCallbackListener adListener;

        public AdRequestBuilder(PublisherAdView publisheradview, String adunitid, int adtype) {
            this.adUnitId = adunitid;
            this.adType = adtype;
            this.publisherAdView = publisheradview;
        }


        public AdRequestBuilder setAdListener(AdCallbackListener managerListener) {
            this.adListener = managerListener;
            return this;
        }

        public AdRequest build() {
            return new AdRequest(this);
        }

    }

}