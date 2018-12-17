package com.dfp.test.utils;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Handler;

import com.dfp.test.activities.DFPDelayActivity;

/**
 * it gives callback to {@link DFPDelayActivity#onTimeExpire()}
 * {@link #NUMBER_OF_CALLS_TOGGETHER} times after {@link #DELAY_IN_MIILI} delay
 */
public class RepeatCaller implements LifecycleObserver {
    /**
     * How many ad calls are made at a time after time out
     */
    private static int NUMBER_OF_CALLS_TOGGETHER = 1;

    /**
     * Delay in between calls
     */
    private static int DELAY_IN_MIILI = 2000;
    private RepeatCallListener mRepeatCallListener;
    private final Lifecycle mLifecycle;
    private int delayinbetweencalls, numberofcallstogether;

    public RepeatCaller(Lifecycle lifecycle) {
        this(lifecycle, DELAY_IN_MIILI, NUMBER_OF_CALLS_TOGGETHER);
    }

    public RepeatCaller(Lifecycle lifecycle, int dely, int callstoggether) {
        this.mLifecycle = lifecycle;
        this.delayinbetweencalls = dely;
        this.numberofcallstogether = callstoggether;

        this.mLifecycle.addObserver(this);
    }

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
            handler.postDelayed(this, delayinbetweencalls);
            if (mRepeatCallListener != null) {
                for (int i = 0; i < numberofcallstogether; i++) {
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
        this.mLifecycle.removeObserver(this);
    }

    public interface RepeatCallListener {
        void onTimeExpire();
    }

}
