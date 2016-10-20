/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sonhnlab.pc.constructionvehicle.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sonhnlab.pc.constructionvehicle.R;
import com.sonhnlab.pc.constructionvehicle.adapters.DeviceAdapter;
import com.sonhnlab.pc.constructionvehicle.entities.Device;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

	//region Properties

    private View vLine;

    private ListView mListDevices;

    private BluetoothAdapter mBluetoothAdapter = null;

    private Set<BluetoothDevice> mPairedDevices;

    public static String EXTRA_ADDRESS = "device_address";

    public static String TAG = "BluetoothActivity";

    //endregion

    //region Override method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        vLine = (View) findViewById(R.id.v_line);
        vLine.getBackground().setAlpha(128);

        mListDevices = (ListView) findViewById(R.id.list_devices);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth device not available", Toast.LENGTH_SHORT).show();

            finish();
        }
        else if (!mBluetoothAdapter.isEnabled()) {
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon, 1);
        }

        pairedDevicesList();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //endregion

    //region Private method

    private void pairedDevicesList() {
        mPairedDevices = mBluetoothAdapter.getBondedDevices();
        ArrayList<Device> listDevices = new ArrayList<Device>();

        if (mPairedDevices.size() > 0) {
            for (BluetoothDevice bt : mPairedDevices) {
                Device newDevice = new Device(bt.getName(), bt.getAddress());
                listDevices.add(newDevice);
            }
        } else {
            Toast.makeText(getApplicationContext(), "No paired Bluetooth devices found", Toast.LENGTH_SHORT).show();
        }

        final DeviceAdapter adapter = new DeviceAdapter(this, listDevices);
        mListDevices.setAdapter(adapter);
        mListDevices.setOnItemClickListener(mClickListener);
    }

    private AdapterView.OnItemClickListener mClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            TextView tvAddress = (TextView) view.findViewById(R.id.tv_address);
            String address = tvAddress.getText().toString();

//            String info = ((TextView) view).getText().toString();
//            Log.d(TAG, "abc" + info.length());
//            String address = info.substring(info.length() - 17);

            Intent intent = new Intent(BluetoothActivity.this, MainActivity.class);
            intent.putExtra(EXTRA_ADDRESS, address);
            startActivity(intent);
        }
    };

    //endregion
}
