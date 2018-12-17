package com.dfp.test.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dfp.test.R;
import com.dfp.test.ads.AdCallbackListener;
import com.dfp.test.ads.AdManager;
import com.dfp.test.ads.AdRequest;
import com.dfp.test.utils.UnitIdUtils;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

/**
 * This activity makes a call for the dfp server and displays the ad
 * Aslo updating RAM after every 500 ms
 */
public class DFPRamActivity extends AppCompatActivity implements AdCallbackListener, View.OnClickListener {
    private LinearLayout mAdcontainer;
    private Button mRest, mRequest;


    private UnitIdUtils mUnitIdUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ram);
        mAdcontainer = findViewById(R.id.ad_container);
        mRequest = findViewById(R.id.btn_request_ad);
        mRest = findViewById(R.id.btn_reset);
        mRest.setOnClickListener(this);
        mRequest.setOnClickListener(this);


        //Unit id are provided in cyclic manager from {@link UnitIdUtils}
        mUnitIdUtils = new UnitIdUtils();
    }


    /**
     * Make a call to fetch new ad for unitIdItem
     */
    private void loadNextAd(UnitIdUtils.UnitIdItem unitIdItem) {
        PublisherAdView mPublisherAdView = new PublisherAdView(this);
        AdRequest.AdRequestBuilder adRequestBuilder = new AdRequest.AdRequestBuilder(mPublisherAdView, unitIdItem.getUnitId(), unitIdItem.getAdType());
        AdRequest adRequest = adRequestBuilder.setAdListener(this).build();
        AdManager.requestAd(adRequest);
    }

    @Override
    public void AdLoaded(PublisherAdView view) {
        mAdcontainer.removeAllViews();
        mAdcontainer.addView(view);
    }

    @Override
    public void AdFailed(int errorCode) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request_ad:

                UnitIdUtils.UnitIdItem unitIdItem = mUnitIdUtils.getNextUnitId();
                loadNextAd(unitIdItem);
                break;
            case R.id.btn_reset:
                if (mAdcontainer.getChildCount() > 0) {
                    PublisherAdView publisherAdView = (PublisherAdView) mAdcontainer.getChildAt(0);
                    publisherAdView.destroy();
                }

                mAdcontainer.removeAllViews();
                break;
        }
    }


}
