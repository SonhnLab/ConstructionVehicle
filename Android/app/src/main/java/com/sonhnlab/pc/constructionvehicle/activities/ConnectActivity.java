package com.sonhnlab.pc.constructionvehicle.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sonhnlab.pc.constructionvehicle.R;

public class ConnectActivity extends AppCompatActivity {

    //region Properties

    ImageButton ibConnect;

    private static long sBackPressed;

    private BluetoothAdapter mBluetoothAdapter = null;

    //endregion

    //region Override method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        ibConnect = (ImageButton) findViewById(R.id.ib_connect);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth device not available", Toast.LENGTH_SHORT).show();

            finish();
        }

        ibConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnBTon, 1);
                } else {
                    Intent intent = new Intent(ConnectActivity.this, BluetoothActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (sBackPressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press one again to exit", Toast.LENGTH_SHORT).show();
            sBackPressed = System.currentTimeMillis();
        }
    }

    //endregion
}
