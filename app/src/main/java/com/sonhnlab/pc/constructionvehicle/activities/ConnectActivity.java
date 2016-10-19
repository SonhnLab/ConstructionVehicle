package com.sonhnlab.pc.constructionvehicle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sonhnlab.pc.constructionvehicle.R;

public class ConnectActivity extends AppCompatActivity {

    //region Properties

    ImageView ivConnect;

    TextView tvConnect;

    private static long sBackPressed;

    //endregion

    //region Override method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        ivConnect = (ImageView) findViewById(R.id.iv_connect);

        tvConnect = (TextView) findViewById(R.id.tv_connect);

        ivConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConnectActivity.this, BluetoothActivity.class);
                startActivity(intent);
            }
        });

        tvConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConnectActivity.this, BluetoothActivity.class);
                startActivity(intent);
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
