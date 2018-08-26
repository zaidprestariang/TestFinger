package com.example.zaidshaharil.testfinger1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DetaineeListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Detainee> mDetaineeList;

    public DetaineeListAdapter(Context mContext, List<Detainee> mDetaineeList) {
        this.mContext = mContext;
        this.mDetaineeList = mDetaineeList;
    }

    @Override
    public int getCount() {
        return mDetaineeList.size();
    }

    @Override
    public Object getItem(int i) {
        return mDetaineeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.item_detainee_list, null);
        TextView tvName = v.findViewById(R.id.tblDetainee_name);
        TextView tvDeviceId = v.findViewById(R.id.tblDetainee_deviceId);
        TextView tvStatus = v.findViewById(R.id.tblDetainee_status);

        tvName.setText(mDetaineeList.get(i).getName());
        tvDeviceId.setText(mDetaineeList.get(i).getDeviceId());
        tvStatus.setText(mDetaineeList.get(i).getStatus());

        v.getTag(mDetaineeList.get(i).getId());
        return v;
    }


}
