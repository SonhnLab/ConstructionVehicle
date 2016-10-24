package com.sonhnlab.pc.constructionvehicle.entities;

/**
 * Created by PC on 10/7/2016.
 */

public class Device {

    //region Properties

    private String mName;

    private String mAddress;

    //endregion

    //region Getter

    public String getName() {
        return mName;
    }

    public String getAddress() {
        return mAddress;
    }

    //endregion

    //region Constructor

    public Device(String name, String address) {
        mName = name;
        mAddress = address;
    }

    //endregion
}
