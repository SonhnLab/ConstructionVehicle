package com.sonhnlab.pc.constructionvehicle.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sonhnlab.pc.constructionvehicle.R;
import com.sonhnlab.pc.constructionvehicle.entities.Joystick;

public class MainActivity extends AppCompatActivity {

    RelativeLayout mLayout;
    Joystick mJoystick;
    TextView tv_x, tv_y, tv_angle, tv_distance, tv_direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_x = (TextView) findViewById(R.id.tv_x);
        tv_y = (TextView) findViewById(R.id.tv_y);
        tv_angle = (TextView) findViewById(R.id.tv_angle);
        tv_distance = (TextView) findViewById(R.id.tv_distance);
        tv_direction = (TextView) findViewById(R.id.tv_direction);

        mLayout = (RelativeLayout)findViewById(R.id.layout_joystick);

        mJoystick = new Joystick(getApplicationContext(), mLayout, R.drawable.button_control);
        mJoystick.setStickSize(400, 400);
        mJoystick.setLayoutSize(1000, 1000);
        mJoystick.setLayoutAlpha(150);
        mJoystick.setStickAlpha(100);
        mJoystick.setOffset(200);
        mJoystick.setMinimumDistance(50);

        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mJoystick.drawStick(motionEvent);
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN
                        || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    tv_x.setText("X: " + String.valueOf(mJoystick.getX()));
                    tv_y.setText("Y: " + String.valueOf(mJoystick.getY()));
                    tv_angle.setText("Angle: " + String.valueOf(mJoystick.getAngle()));
                    tv_distance.setText("Distance: " + String.valueOf(mJoystick.getDistance()));

                    int direction = mJoystick.get8Direction();
                    if(direction == Joystick.STICK_UP) {
                        tv_direction.setText("Direction : Up");
                    } else if(direction == Joystick.STICK_UPRIGHT) {
                        tv_direction.setText("Direction : Up Right");
                    } else if(direction == Joystick.STICK_RIGHT) {
                        tv_direction.setText("Direction : Right");
                    } else if(direction == Joystick.STICK_DOWNRIGHT) {
                        tv_direction.setText("Direction : Down Right");
                    } else if(direction == Joystick.STICK_DOWN) {
                        tv_direction.setText("Direction : Down");
                    } else if(direction == Joystick.STICK_DOWNLEFT) {
                        tv_direction.setText("Direction : Down Left");
                    } else if(direction == Joystick.STICK_LEFT) {
                        tv_direction.setText("Direction : Left");
                    } else if(direction == Joystick.STICK_UPLEFT) {
                        tv_direction.setText("Direction : Up Left");
                    } else if(direction == Joystick.STICK_NONE) {
                        tv_direction.setText("Direction : Center");
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    tv_x.setText("X :");
                    tv_y.setText("Y :");
                    tv_angle.setText("Angle :");
                    tv_distance.setText("Distance :");
                    tv_direction.setText("Direction :");
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
