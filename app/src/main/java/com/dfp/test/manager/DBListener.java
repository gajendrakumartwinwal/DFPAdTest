package com.dfp.test.manager;

import com.dfp.test.database.AdInfo;

import java.util.List;

public interface DBListener {
    void onDataLoaded(List<AdInfo> items);
}
