package com.example.bluetoothserviceapplication.BTservice;

import android.app.Application;
import android.os.Handler;

public class MyApplicationSingleton extends Application {

    // private static instance variable to hold the singleton instance
    private static volatile MyApplicationSingleton INSTANCE = null;
    Handler.Callback realCallback = null;
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (realCallback != null) {
                realCallback.handleMessage(msg);
            }
        };
    };

    // private constructor to prevent instantiation of the class
    private MyApplicationSingleton() {}

    // public static method to retrieve the singleton instance
    public static MyApplicationSingleton getInstance() {
        // Check if the instance is already created
        if(INSTANCE == null) {
            // synchronize the block to ensure only one thread can execute at a time
            synchronized (MyApplicationSingleton.class) {
                // check again if the instance is already created
                if (INSTANCE == null) {
                    // create the singleton instance
                    INSTANCE = new MyApplicationSingleton();
                }
            }
        }
        // return the singleton instance
        return INSTANCE;
    }

    public Handler getHandler() {
        return handler;
    }
    public void setCallBack(Handler.Callback callback) {
        this.realCallback = callback;
    }
}
