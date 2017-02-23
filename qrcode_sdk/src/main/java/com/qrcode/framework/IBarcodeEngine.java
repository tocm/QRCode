package com.qrcode.framework;

import android.content.Context;
import android.content.Intent;


/**
 * Created by Andy.chen on 2017/2/22.
 */

public interface IBarcodeEngine {


    public void enabledLog(boolean flag);
    public void init();
    public void stop();

    /**
     * start a scan barcode event
     * @param context
     */
    public void scanBarcode(Context context);

    /**
     * start a generate barcode event
     * @param context
     * @param generateContent
     */
    public void generateBarcode(Context context, String generateContent);

    /**
     *
     * 注册消息回调
     * @param barcodeEventsListener
     * Necessary
     */
    public void setBarcodeEventsListener(IBarcodeEventsListener barcodeEventsListener);

    /**
     * 监听ACTIVITY 接口
     * Necessary
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data);

}
