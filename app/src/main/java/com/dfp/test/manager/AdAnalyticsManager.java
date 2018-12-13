package com.dfp.test.manager;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;

import com.dfp.test.MyApplication;
import com.dfp.test.database.AdInfo;
import com.dfp.test.database.AppDatabase;

import java.util.List;

/**
 * This class store all the header, footer & mrec ad details call including
 * 1. DFP SDK response time
 * 2. Error code or success
 * <p>
 * Also manage previous data is shared preference
 */
public class AdAnalyticsManager {

    private static AdAnalyticsManager mInstance;
    AppDatabase db;

    private AdAnalyticsManager() {
        db = Room.databaseBuilder(MyApplication.getInstance(),
                AppDatabase.class, "database-name").build();
    }

    public static AdAnalyticsManager getInstance() {
        if (mInstance == null) mInstance = new AdAnalyticsManager();
        return mInstance;
    }

    public void insertAdResponse(AdInfo adInfo) {
        InsertTask task = new InsertTask();
        task.execute(adInfo);
    }

    public void getAll(DBListener dbListener) {
        FindTask task = new FindTask(dbListener);
        task.execute();
    }

    /**
     * Async task to execute db insert query
     */
    class InsertTask extends AsyncTask<AdInfo, Void, Void> {

        @Override
        protected Void doInBackground(AdInfo... adInfos) {
            db.adInfoDAO().insertAll(adInfos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    /**
     * Async task to execute db data fetch query
     */
    class FindTask extends AsyncTask<Void, Void, List<AdInfo>> {
        DBListener mDBListener;

        public FindTask(DBListener DBListener) {
            mDBListener = DBListener;
        }

        @Override
        protected List<AdInfo> doInBackground(Void... adInfos) {
            return db.adInfoDAO().getAll();
        }

        @Override
        protected void onPostExecute(List<AdInfo> items) {
            mDBListener.onDataLoaded(items);
        }
    }

}
