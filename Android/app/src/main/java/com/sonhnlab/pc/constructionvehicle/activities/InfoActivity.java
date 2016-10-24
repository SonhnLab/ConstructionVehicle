package com.sonhnlab.pc.constructionvehicle.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sonhnlab.pc.constructionvehicle.R;

public class InfoActivity extends AppCompatActivity {

    //region Properties

    TextView tvAppInfo;

    //endregion

    //region Override method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvAppInfo = (TextView) findViewById(R.id.tv_app_info);
        Typeface Russell = Typeface.createFromAsset(getAssets(), "fonts/Russell Square.ttf");
        tvAppInfo.setTypeface(Russell);
    }

    //endregion
}
