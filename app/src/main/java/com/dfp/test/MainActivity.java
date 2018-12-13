package com.dfp.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dfp.test.ads.AdCallbackListener;
import com.dfp.test.ads.AdManager;
import com.dfp.test.ads.AdRequest;
import com.dfp.test.utils.LogUtil;
import com.dfp.test.utils.RepeatCaller;
import com.dfp.test.utils.UnitIdUtils;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

/**
 * This activity is subactivity of {@link ActionBarActivity} which creates option menu for ad-report and launch {@link ReportActivity} when user clicks on optoin menu
 * <p>
 * This activity make ad call on repeated time period of 1 sec for {@link UnitIdUtils} and 3 ad calls at a time will be called to change this refer to: {@link RepeatCaller}
 * <p>
 * Repeated calls are trigged from {@link RepeatCaller}
 * <p>
 * Ad calls are made using {@link AdManager}
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener, RepeatCaller.RepeatCallListener, AdCallbackListener {

    private LinearLayout mAdContainer;

    private Button mStart, mStop;
    private TextView mResponseLog, mCurrentUnitId;

    private UnitIdUtils mUnitIdUtils;
    private PublisherAdView mPublisherAdView;

    private RepeatCaller mRepeatCaller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initViews();
    }

    private void initViews() {
        mAdContainer = findViewById(R.id.ad_view_container);
        mResponseLog = findViewById(R.id.tv_report);
        mStart = findViewById(R.id.btn_start);
        mStop = findViewById(R.id.btn_stop);
        mCurrentUnitId = findViewById(R.id.current_unitid);

        //Click listeners giving callback to {@link #onClick}
        mStart.setOnClickListener(this);
        mStop.setOnClickListener(this);

        //Repeated callback caller after each 10 sec into {@link #onTimeExpire}
        mRepeatCaller = new RepeatCaller();
        getLifecycle().addObserver(mRepeatCaller);

        //Unit id are provided in cyclic manager from {@link UnitIdUtils}
        mUnitIdUtils = new UnitIdUtils();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                //callback will be giving after each 10 sec into {@link #onTimeExpire} method
                mRepeatCaller.registerRepeatCallback(this);
                break;
            case R.id.btn_stop:
                //callback will be stoped into {@link #onTimeExpire} method
                mRepeatCaller.unRegisterRepeatCallback(this);
                break;
        }
    }

    @Override
    public void onTimeExpire() {
        //Remove all ads if added previously in container
        mAdContainer.removeAllViews();

        UnitIdUtils.UnitIdItem unitIdItem = mUnitIdUtils.getNextUnitId();

        //Set current unitid
        mCurrentUnitId.setText(unitIdItem.getUnitId());

        //Set current ad status as loading to the textview
        mResponseLog.setText("Loading Ad...");
        //Make a call to fetch new ad for unitIdItem
        loadNextAd(unitIdItem);
    }


    /**
     * Make a call to fetch new ad for unitIdItem
     */
    private void loadNextAd(UnitIdUtils.UnitIdItem unitIdItem) {
        mPublisherAdView = new PublisherAdView(this);
        AdRequest.AdRequestBuilder adRequestBuilder = new AdRequest.AdRequestBuilder(mPublisherAdView, unitIdItem.getUnitId(), unitIdItem.getAdType());
        AdRequest adRequest = adRequestBuilder.setAdListener(this).build();
        AdManager.requestAd(adRequest);
    }


    @Override
    public void AdLoaded(PublisherAdView view) {// Called when ad is loaded successfully
        mAdContainer.removeAllViews();
        mAdContainer.addView(view);
        //Showing logs for ad success in textview
        mResponseLog.setText(LogUtil.getAdLogs());
        mResponseLog.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public void AdFailed(int errorCode) {// Called when ad is failed due to some reason
        mAdContainer.removeAllViews();
        //Show error log for ad
        mResponseLog.setText(LogUtil.getAdLogs());
        mResponseLog.setTextColor(getResources().getColor(R.color.red));
    }
}
