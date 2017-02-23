package com.qrcode.framework;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.qrcode.framework.util.LogUtils;


public class  BarcodeResultHandler extends Handler {
    
    public final static String TAG = BarcodeResultHandler.class.getSimpleName();
    public final static int CB_HANDLER_MSG_TYPE_SCAN = 10;
    public final static int CB_HANDLER_MSG_TYPE_SCAN_CANCEL = 11;
    public final static int CB_HANDLER_MSG_TYPE_GENERATION = 12;
    public final static int CB_HANDLER_MSG_TYPE_GENERATION_CANCEL = 13;

    public final static String CB_BUNDLE_KEY_CONTENTS = "ub.barcode.cb.key.CONTENTS";
    public final static String CB_BUNDLE_KEY_FORMAT = "ub.barcode.cb.key.FORMAT";
    public final static String CB_BUNDLE_KEY_TYPE = "ub.barcode.cb.key.TYPE";
    public final static String CB_BUNDLE_KEY_LABEL = "ub.barcode.cb.key.LABEL";
    public final static String CB_BUNDLE_KEY_CREATETIME = "ub.barcode.cb.key.CREATETIME";
    public final static String CB_BUNDLE_KEY_BITMAP = "ub.barcode.cb.key.BITMAP";


    public BarcodeResultHandler(Activity activity) {
    }

    @Override
    public void handleMessage(Message msg) {
        // TODO Auto-generated method stub
        super.handleMessage(msg);
        int type = msg.what;
        LogUtils.d(TAG, "BarcodeResultHandler type = "+type);
        switch(type) {
        case CB_HANDLER_MSG_TYPE_SCAN:
            Bundle bundle = (Bundle) msg.obj;
            String contents = bundle.getString(BarcodeResultHandler.CB_BUNDLE_KEY_CONTENTS);
            byte[] bmdata = bundle.getByteArray(BarcodeResultHandler.CB_BUNDLE_KEY_BITMAP);
            LogUtils.d(TAG, "BarcodeResultHandler SCAN contents = "+contents +",bmdata = "+bmdata.length);
            break;
        case CB_HANDLER_MSG_TYPE_GENERATION:
            LogUtils.d(TAG, "BarcodeResultHandler generation ");
            Bundle bd = (Bundle) msg.obj;
            String displayContents = bd.getString(BarcodeResultHandler.CB_BUNDLE_KEY_CONTENTS);
            LogUtils.d(TAG, "BarcodeResultHandler generation contents = "+displayContents);
            break;
        }
      
    }

}
