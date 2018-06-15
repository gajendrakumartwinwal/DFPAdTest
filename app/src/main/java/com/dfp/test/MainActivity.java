package com.dfp.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private PublisherAdView mGoogleAd, mMrecAd;

    private Button mGoogleAdButton, mMrecAdButton;
    private TextView mResponseLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        initViews();

        mGoogleAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                setLogText(false);
                mGoogleAd.setVisibility(View.INVISIBLE);
                Log.d("AD_Response", "Google Failed with " + i);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                setLogText(true);
                mGoogleAd.setVisibility(View.VISIBLE);
                Log.d("AD_Response", "Google success");
            }
        });
        mMrecAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                mMrecAd.setVisibility(View.INVISIBLE);
                Log.d("AD_Response", "MREC Failed with " + i);
                setLogText(false);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mMrecAd.setVisibility(View.VISIBLE);
                Log.d("AD_Response", "MREC success");
                setLogText(true);
            }
        });
    }

    private void initViews() {
        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mGoogleAd = findViewById(R.id.ad_view_google);
        mMrecAd = findViewById(R.id.ad_view_mrec);
        mResponseLog = findViewById(R.id.tv_report);
//        setMrecAdSize();

        mGoogleAdButton = findViewById(R.id.google_ad);
        mMrecAdButton = findViewById(R.id.mrec_ad);

        mGoogleAdButton.setOnClickListener(this);
        mMrecAdButton.setOnClickListener(this);
        mMrecAdButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "1. Mrec Ad UnitID:\n" + mMrecAd.getAdUnitId() + "\n2. Size:\n" + mMrecAd.getAdSize().toString(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        mGoogleAdButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "1. Google Ad UnitID:\n" + mMrecAd.getAdUnitId() + "\n2. Size:\n" + mMrecAd.getAdSize().toString(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    private void loadGoogleAd() {
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();

        // Start loading the ad in the background.
        mGoogleAd.loadAd(adRequest);
    }

    private void loadMrecAd() {
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();

        // Start loading the ad in the background.
        mMrecAd.loadAd(adRequest);
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        if (mGoogleAd != null) {
            mGoogleAd.pause();
        }
        if (mMrecAd != null) {
            mMrecAd.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleAd != null) {
            mGoogleAd.resume();
        }
        if (mMrecAd != null) {
            mMrecAd.resume();
        }
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (mGoogleAd != null) {
            mGoogleAd.destroy();
        }
        if (mMrecAd != null) {
            mMrecAd.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        mResponseLog.setText("Loading Ad...");
        switch (v.getId()) {
            case R.id.google_ad:
                loadGoogleAd();
                break;
            case R.id.mrec_ad:
                loadMrecAd();
                break;
        }
    }

    private void setLogText(boolean isSuccess) {
        try {
            Process process = Runtime.getRuntime().exec("logcat -d Ads");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    if (line.contains("Ads"))
                        log.append(line.substring(line.indexOf("Ads")) + "\n");
                } catch (Exception e) {
                }
            }
            mResponseLog.setText(log.toString());
            if (isSuccess)
                mResponseLog.setTextColor(getResources().getColor(android.R.color.black));
            else
                mResponseLog.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } catch (IOException e) {
        }
        //Clear the logcat
        try {
            Runtime.getRuntime().exec("logcat -c Ads");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
