package com.sonhnlab.pc.constructionvehicle.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sonhnlab.pc.constructionvehicle.R;

public class SplashActivity extends AppCompatActivity {

    //region Properties

    private static int SPLASH_TIME_OUT = 4000;

    TextView tvSplash;

    //endregion

    //region Override method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvSplash = (TextView) findViewById(R.id.tv_splash);

        Typeface Russell = Typeface.createFromAsset(getAssets(), "fonts/Russell Square.ttf");
        tvSplash.setTypeface(Russell);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ConnectActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    //endregion
}
