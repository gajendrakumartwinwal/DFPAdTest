package com.dfp.test.utils;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Handler;

import com.dfp.test.MainActivity;

/**
 * it gives callback to {@link MainActivity#onTimeExpire()}
 * {@link #NUMBER_OF_CALLS_TOGGETHER} times after {@link #DELAY_IN_MIILI} delay
 */
public class RepeatCaller implements LifecycleObserver {
    /**
     * How many ad calls are made at a time after time out
     */
    private static int NUMBER_OF_CALLS_TOGGETHER = 3;

    /**
     * Delay in between calls
     */
    private static int DELAY_IN_MIILI = 1000;
    private RepeatCallListener mRepeatCallListener;

    public void registerRepeatCallback(RepeatCallListener repeatCallListener) {
        mRepeatCallListener = repeatCallListener;
    }

    public void unRegisterRepeatCallback(RepeatCallListener repeatCallListener) {
        mRepeatCallListener = null;
    }

    Handler handler = new Handler();

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, DELAY_IN_MIILI);
            if (mRepeatCallListener != null) {
                for (int i = 0; i < NUMBER_OF_CALLS_TOGGETHER; i++) {
                    mRepeatCallListener.onTimeExpire();
                }
            }
        }
    };

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        handler.post(runnableCode);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        handler.removeCallbacks(runnableCode);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        mRepeatCallListener = null;
    }

    public interface RepeatCallListener {
        void onTimeExpire();
    }

}
