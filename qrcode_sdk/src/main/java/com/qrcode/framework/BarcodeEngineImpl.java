package com.qrcode.framework;

import android.content.Context;

import com.qrcode.framework.util.LogUtils;


/**
 * Created by Andy.chen on 2017/2/22.
 */
public class BarcodeEngineImpl extends BaseBarcode implements IBarcodeEngine {
    private final static String TAG = BarcodeEngineImpl.class.getSimpleName();
    private IBarcodeEventsListener mIBarcodeEventsListener;

    @Override
    public IBarcodeEventsListener getBarcodeEventsListener() {
        return mIBarcodeEventsListener;
    }


    @Override
    public void enabledLog(boolean flag) {
        LogUtils.enableLog(flag);
    }

    @Override
    public void init() {

    }

    @Override
    public void stop() {
        mIBarcodeEventsListener = null;
    }

    @Override
    public void scanBarcode(Context context) {
        startScanBarcode(context);
    }

    @Override
    public void generateBarcode(Context context, String generateContent) {
        startGenerateBarcode(context,generateContent);
    }

    @Override
    public void setBarcodeEventsListener(IBarcodeEventsListener barcodeEventsListener) {
        this.mIBarcodeEventsListener = barcodeEventsListener;
    }
}
