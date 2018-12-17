package com.dfp.test.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dfp.test.R;
import com.dfp.test.database.AdInfo;
import com.dfp.test.manager.AdAnalyticsManager;
import com.dfp.test.manager.DBListener;
import com.dfp.test.utils.LogUtil;

import java.util.List;

public class ReportActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        mRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setAdapter();
    }

    private void setAdapter() {
        AdAnalyticsManager.getInstance().getAll(new DBListener() {
            @Override
            public void onDataLoaded(List<AdInfo> items) {
                RecyclerAdapter adapter = new RecyclerAdapter(items);
                mRecyclerView.setAdapter(adapter);
            }
        });
    }


    /***************************   Adapter for recyclerview   ************************/
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

        private List<AdInfo> adItemsList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView mType, mUnitId, mStatus, mTime;

            public MyViewHolder(View view) {
                super(view);
                mType = (TextView) view.findViewById(R.id.ad_type);
                mUnitId = (TextView) view.findViewById(R.id.ad_unitid);
                mStatus = (TextView) view.findViewById(R.id.ad_status);
                mTime = (TextView) view.findViewById(R.id.ad_time);
            }
        }


        public RecyclerAdapter(List<AdInfo> moviesList) {
            this.adItemsList = moviesList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.report_item_view, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            AdInfo adInfo = adItemsList.get(position);
            holder.mType.setText(LogUtil.getAdName(adInfo.getAdtype()));
            holder.mUnitId.setText(adInfo.getUnitid());
            holder.mStatus.setText("Status: " + adInfo.getStatus());
            holder.mTime.setText("Time:    " + adInfo.getTime());

            if ("Success".equalsIgnoreCase(adInfo.getStatus())) {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.header));
            } else {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.red));
            }
        }

        @Override
        public int getItemCount() {
            return adItemsList.size();
        }
    }
}
