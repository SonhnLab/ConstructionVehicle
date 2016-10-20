package com.sonhnlab.pc.constructionvehicle.activities;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
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

        //region Move Forward

        ivUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moveForward();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivUp.setImageDrawable(getResources().getDrawable(R.drawable.ic_up_press, getApplicationContext().getTheme()));
                        } else {
                            ivUp.setImageDrawable(getResources().getDrawable(R.drawable.ic_up_press));
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        stop();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivUp.setImageDrawable(getResources().getDrawable(R.drawable.ic_up, getApplicationContext().getTheme()));
                        } else {
                            ivUp.setImageDrawable(getResources().getDrawable(R.drawable.ic_up));
                        }
                        return true;
                }
                return false;
            }
        });

        //endregion

        //region Move Backward

        ivDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moveBackward();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivDown.setImageDrawable(getResources().getDrawable(R.drawable.ic_down_press, getApplicationContext().getTheme()));
                        } else {
                            ivDown.setImageDrawable(getResources().getDrawable(R.drawable.ic_down_press));
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        stop();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivDown.setImageDrawable(getResources().getDrawable(R.drawable.ic_down, getApplicationContext().getTheme()));
                        } else {
                            ivDown.setImageDrawable(getResources().getDrawable(R.drawable.ic_down));
                        }
                        return true;
                }
                return false;
            }
        });

        //endregion

        //region Turn Left

        ivLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        turnLeft();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_left_press, getApplicationContext().getTheme()));
                        } else {
                            ivLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_left_press));
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        stop();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_left, getApplicationContext().getTheme()));
                        } else {
                            ivLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_left));
                        }
                        return true;
                }
                return false;
            }
        });

        //endregion

        //region Turn Right

        ivRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        turnRight();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivRight.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_press, getApplicationContext().getTheme()));
                        } else {
                            ivRight.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_press));
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        stop();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivRight.setImageDrawable(getResources().getDrawable(R.drawable.ic_right, getApplicationContext().getTheme()));
                        } else {
                            ivRight.setImageDrawable(getResources().getDrawable(R.drawable.ic_right));
                        }
                        return true;
                }
                return false;
            }
        });

        //endregion

        //region Lift Up

        ivLiftUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        liftUp();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivLiftUp.setImageDrawable(getResources().getDrawable(R.drawable.ic_lift_up_press, getApplicationContext().getTheme()));
                        } else {
                            ivLiftUp.setImageDrawable(getResources().getDrawable(R.drawable.ic_lift_up_press));
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        stop();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivLiftUp.setImageDrawable(getResources().getDrawable(R.drawable.ic_lift_up, getApplicationContext().getTheme()));
                        } else {
                            ivLiftUp.setImageDrawable(getResources().getDrawable(R.drawable.ic_lift_up));
                        }
                        return true;
                }
                return false;
            }
        });

        //endregion

        //region Lift Down

        ivLifpDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        liftDown();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivLifpDown.setImageDrawable(getResources().getDrawable(R.drawable.ic_lift_down_press, getApplicationContext().getTheme()));
                        } else {
                            ivLifpDown.setImageDrawable(getResources().getDrawable(R.drawable.ic_lift_down_press));
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        stop();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivLifpDown.setImageDrawable(getResources().getDrawable(R.drawable.ic_lift_down, getApplicationContext().getTheme()));
                        } else {
                            ivLifpDown.setImageDrawable(getResources().getDrawable(R.drawable.ic_lift_down));
                        }
                        return true;
                }
                return false;
            }
        });

        //endregion

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_disconnect) {
            disconnect();
        }

        return super.onOptionsItemSelected(item);
    }

    //endregion

    //region Control Car method

    private void moveForward() {
        if (mBluetoothSocket != null) {
            try {
                mBluetoothSocket.getOutputStream().write(1);
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void moveBackward() {
        if (mBluetoothSocket != null) {
            try {
                mBluetoothSocket.getOutputStream().write(2);
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void turnLeft() {
        if (mBluetoothSocket != null) {
            try {
                mBluetoothSocket.getOutputStream().write(3);
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void turnRight() {
        if (mBluetoothSocket != null) {
            try {
                mBluetoothSocket.getOutputStream().write(4);
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void liftUp() {
        if (mBluetoothSocket != null) {
            try {
                mBluetoothSocket.getOutputStream().write(5);
                byte[] x = "LU".toString().getBytes();
                x.toString();
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void liftDown() {
        if (mBluetoothSocket != null) {
            try {
                mBluetoothSocket.getOutputStream().write(6);
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void stop() {
        if (mBluetoothSocket != null) {
            try {
                mBluetoothSocket.getOutputStream().write(0);
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    //endregion

    //region Private method

    private void disconnect() {
        if (mBluetoothSocket != null) {
            try {
                mBluetoothSocket.close();
                msg("Disconnected");
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
