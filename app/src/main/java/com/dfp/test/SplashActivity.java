package com.dfp.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dfp.test.activities.DFPDelayActivity;
import com.dfp.test.activities.DFPRamActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViewById(R.id.btn_dfp_delay).setOnClickListener(this);
        findViewById(R.id.btn_dfp_ram).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dfp_delay:
                startActivity(new Intent(this, DFPDelayActivity.class));
                break;
            case R.id.btn_dfp_ram:
                startActivity(new Intent(this, DFPRamActivity.class));
                break;
        }
    }
}
