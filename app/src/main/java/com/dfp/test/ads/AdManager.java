package com.dfp.test.ads;

import android.util.Log;

import com.dfp.test.database.AdInfo;
import com.dfp.test.manager.AdAnalyticsManager;
import com.dfp.test.utils.LogUtil;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;

public class AdManager {

    public static void requestAd(final AdRequest adRequest) {
        //Init adinfo item
        final AdInfo adInfo = buildAdInfoItem(adRequest);
        PublisherAdRequest publisherAdRequest = new PublisherAdRequest.Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").build();
        adRequest.getPublisherAdView().setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adRequest.getAdListener().AdLoaded(adRequest.getPublisherAdView());
                insertInDB(adInfo, -1);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                adRequest.getAdListener().AdFailed(errorCode);
                insertInDB(adInfo, errorCode);
            }
        });

        adRequest.getPublisherAdView().loadAd(publisherAdRequest);
    }

    /**
     * Insert adresponse into DB including unitid, time taken to load the ad, status of the ad success or failure
     *
     * @param adInfo
     * @param errorcode
     */
    private static void insertInDB(AdInfo adInfo, int errorcode) {
        adInfo.setTime(System.currentTimeMillis() - adInfo.getId());
        adInfo.setStatus(errorcode == -1 ? "Success" : getErrorName(errorcode));
        //Record analytics
        Log.d("LOAD_AD_TIME", LogUtil.getAdName(adInfo.getAdtype()) + " - " + adInfo.getTime() + " - " + adInfo.getUnitid());
        if (adInfo.getTime() > 4000)
            Log.d("AD_LOAD_TIME_4", LogUtil.getAdName(adInfo.getAdtype()) + " - " + adInfo.getTime() + " - " + adInfo.getUnitid());
        AdAnalyticsManager.getInstance().insertAdResponse(adInfo);
    }

    /**
     * Build AdInfo item to track time take in loading the ad plus other informations
     *
     * @param request
     * @return
     */
    private static AdInfo buildAdInfoItem(AdRequest request) {
        AdInfo adInfo = new AdInfo();
        adInfo.setId(System.currentTimeMillis());
        adInfo.setAdtype(request.getAdType());
        adInfo.setUnitid(request.getAdUnitId());
        return adInfo;
    }


    /**
     * Return string value correspondint to errorcode provided by DFP
     *
     * @param errorCode
     * @return
     */
    public static String getErrorName(int errorCode) {
        switch (errorCode) {
            case 0:
                return "ERROR_CODE_INTERNAL_ERROR";
            case 1:
                return "ERROR_CODE_INVALID_REQUEST";
            case 2:
                return "ERROR_CODE_NETWORK_ERROR";
            case 3:
                return "ERROR_CODE_NO_FILL";
        }
        return "ERROR_UNKNOWN";
    }
}
