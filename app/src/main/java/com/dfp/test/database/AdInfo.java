package com.dfp.test.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class AdInfo {
    //Unique identifier for individual request
    @PrimaryKey
    long id;

    //Unit id for the ad
    @ColumnInfo(name = "unitid")
    String unitid;

    //Ad type is it header ad = 1, footer ad = 2 or mrec ad = 3
    @ColumnInfo(name = "adtype")
    int adtype;

    //DFP response time between making call to DFP SDK and response from DFP SDK(failed or success)
    @ColumnInfo(name = "time")
    long time;

    //status of the call it is succss or failed
    @ColumnInfo(name = "status")
    String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public int getAdtype() {
        return adtype;
    }

    public void setAdtype(int adtype) {
        this.adtype = adtype;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
