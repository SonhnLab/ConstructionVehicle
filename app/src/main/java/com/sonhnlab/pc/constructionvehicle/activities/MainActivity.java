package com.sonhnlab.pc.constructionvehicle.activities;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.sonhnlab.pc.constructionvehicle.R;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //region Properties

    private ImageView ivUp, ivDown, ivLeft, ivRight, ivLiftUp, ivLifpDown;

    private String mAddress = null;

    private ProgressDialog mProgressDialog;

    BluetoothAdapter mBluetoothAdapter = null;

    BluetoothSocket mBluetoothSocket = null;

    private boolean isBTConnected = false;

    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //endregion

    //region Override method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent newIntent = getIntent();
        mAddress = newIntent.getStringExtra(BluetoothActivity.EXTRA_ADDRESS);

        setContentView(R.layout.activity_main);

        ivUp = (ImageView) findViewById(R.id.iv_up);
        ivDown = (ImageView) findViewById(R.id.iv_down);
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        ivLiftUp = (ImageView) findViewById(R.id.iv_lift_up);
        ivLifpDown = (ImageView) findViewById(R.id.iv_lift_down);

        new ConnectBT().execute();


    }

    //endregion

    //region Private method

    private void disconnect() {
        if (mBluetoothSocket != null) {
            try {
                mBluetoothSocket.close();
            } catch (IOException e) {
                msg("Error");
            }
        }
        finish();
    }

    private void msg (String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void> {

        private boolean isConnectSuccess = true;

        @Override
        protected void onPreExecute() {
            mProgressDialog = ProgressDialog.show(MainActivity.this, "Connecting...", "Please wait!!!");
        }

        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if (mBluetoothSocket == null || !isBTConnected) {
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice bluetoothDevice = mBluetoothAdapter.getRemoteDevice(mAddress);
                    mBluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    mBluetoothSocket.connect();
                }

            } catch (IOException e) {
                isConnectSuccess = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (!isConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again");
                finish();
            } else {
                msg("Connected");
                isBTConnected = true;
            }
            mProgressDialog.dismiss();
        }
    }

    //endregion
}
