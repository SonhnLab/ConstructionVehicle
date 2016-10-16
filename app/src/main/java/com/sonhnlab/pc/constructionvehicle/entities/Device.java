package com.sonhnlab.pc.constructionvehicle.entities;

/**
 * Created by PC on 10/7/2016.
 */

public class Device {

    //region Properties

    private String mName;

    private String mAddress;

    private String mSignal;

    //endregion

    //region Getter

    public String getName() {
        return mName;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getSignal() {
        return mSignal;
    }

    //endregion

    //region Constructor

    public Device(String name, String address, Short signal) {
        mName = name;
        mAddress = address;
        mSignal = Short.toString(signal);
    }

    //endregion
}
