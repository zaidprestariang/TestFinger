package com.example.zaidshaharil.testfinger1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class VehicleListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Vehicle> mVehicleList;

    public VehicleListAdapter(Context mContext, List<Vehicle> mVehicleList) {
        this.mContext = mContext;
        this.mVehicleList = mVehicleList;
    }

    @Override
    public int getCount() {
        return mVehicleList.size();
    }

    @Override
    public Object getItem(int i) {
        return mVehicleList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.item_vehicle_list, null);
        TextView tvPlatNo = v.findViewById(R.id.tblItem_plateNo);
        TextView tvGpsId = v.findViewById(R.id.tblItem_gpsId);
        TextView tvVehicleStatus = v.findViewById(R.id.tblItem_vehicleStatus);

        tvPlatNo.setText(mVehicleList.get(i).getPlateNo());
        tvGpsId.setText(mVehicleList.get(i).getDeviceid());
        tvVehicleStatus.setText(mVehicleList.get(i).getStatus());

        v.setTag(mVehicleList.get(i).getId());
        return v;
    }
}
