package com.sonhnlab.pc.constructionvehicle.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sonhnlab.pc.constructionvehicle.R;
import com.sonhnlab.pc.constructionvehicle.entities.Device;

import java.util.ArrayList;

/**
 * Created by PC on 10/19/2016.
 */

public class DeviceAdapter extends ArrayAdapter<Device> {

    //region Construction

    public DeviceAdapter (Context context, ArrayList<Device> devices) {
        super(context, 0 , devices);
    }

    //endregion

    //region Override method

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Device device = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.device_list_item, parent, false);
        }

        int pos = position + 1;

        TextView tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.tv_address);

        tvName.setText(device.getName());
        tvAddress.setText(device.getAddress());
        tvNumber.setText(pos + ". ");
        pos++;

        return convertView;
    }


    //endregion

}
