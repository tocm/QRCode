package com.qrcode.framework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;
import com.qrcode.framework.entity.BarcodeEntity;
import com.qrcode.framework.util.LogUtils;

import static com.qrcode.framework.IBarcodeEventsListener.UpBarcodeEventsStatus.UP_BARCODE_EVENTS_STATUS_CANCEL;
import static com.qrcode.framework.IBarcodeEventsListener.UpBarcodeEventsStatus.UP_BARCODE_EVENTS_STATUS_FAILED;
import static com.qrcode.framework.IBarcodeEventsListener.UpBarcodeEventsStatus.UP_BARCODE_EVENTS_STATUS_SUCCESS;
import static com.qrcode.framework.IBarcodeEventsListener.UpBarcodeEventsType.UP_BARCODE_EVENTS_TYPE_GENERATION;
import static com.qrcode.framework.IBarcodeEventsListener.UpBarcodeEventsType.UP_BARCODE_EVENTS_TYPE_SCAN;


public abstract class BaseBarcode {
    protected final static String TAG = BaseBarcode.class.getSimpleName();
    protected final static int REQUEST_CODE_SCAN = 1;
    protected final static int REQUEST_CODE_GENERATE = 2;

    public abstract IBarcodeEventsListener getBarcodeEventsListener();
    /**
     * get the result from ZXing's CapatureActivityHandler->callBackScanResult();
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       LogUtils.d(TAG, " ---onActivityResult---- requestCode = "+requestCode +", resultCode = "+resultCode);
        switch(requestCode) {
        case REQUEST_CODE_SCAN:
            if (resultCode == Activity.RESULT_OK ) {
                    Bundle bundle = data.getExtras();
                    BarcodeEntity resultDataBean = (BarcodeEntity) bundle.getParcelable(BarcodeEntity.CB_RESULT_KEY);
                    if (resultDataBean != null) {
                        if (getBarcodeEventsListener() != null) {
                            getBarcodeEventsListener().callbackBarcodeData(UP_BARCODE_EVENTS_TYPE_SCAN,UP_BARCODE_EVENTS_STATUS_SUCCESS,resultDataBean);
                        }
//                        String contents = resultDataBean.getContents();
//                        String formats = resultDataBean.getFormat();
//                        String type = resultDataBean.getType();
//                        String createTime = resultDataBean.getCreateTime();
//                        String label = resultDataBean.getLabel();
//                        byte[] bitmap= resultDataBean.getBarcodeBuf();
//                        LogUtils.d(TAG, " ---onActivityResult---- scan successfully");
//                        LogUtils.d(TAG, " contents = "+contents +", formats = "+formats+",type = "+type+",createTime = "+createTime+",label = "+label);
//
//                        bundle.putString(BarcodeResultHandler.CB_BUNDLE_KEY_CONTENTS, contents);
//                        bundle.putString(BarcodeResultHandler.CB_BUNDLE_KEY_FORMAT, formats);
//                        bundle.putString(BarcodeResultHandler.CB_BUNDLE_KEY_TYPE, type);
//                        bundle.putString(BarcodeResultHandler.CB_BUNDLE_KEY_CREATETIME, createTime);
//                        bundle.putString(BarcodeResultHandler.CB_BUNDLE_KEY_LABEL, label);
//                        bundle.putByteArray(BarcodeResultHandler.CB_BUNDLE_KEY_BITMAP,bitmap);
                    }
                if (getBarcodeEventsListener() != null) {
                    getBarcodeEventsListener().callbackBarcodeData(UP_BARCODE_EVENTS_TYPE_SCAN,UP_BARCODE_EVENTS_STATUS_FAILED,null);
                }
               
            } else {
                LogUtils.d(TAG, " ---onActivityResult--- scan failed ");
                if (getBarcodeEventsListener() != null) {
                    getBarcodeEventsListener().callbackBarcodeData(UP_BARCODE_EVENTS_TYPE_SCAN,UP_BARCODE_EVENTS_STATUS_CANCEL,null);
                }
            }
            break;
        case REQUEST_CODE_GENERATE:
            if (resultCode == Activity.RESULT_OK ) {
                    Bundle bundle = data.getExtras();
                    BarcodeEntity barcodeEntity = bundle.getParcelable(BarcodeEntity.CB_RESULT_KEY);
                    if (barcodeEntity != null) {
                        if (getBarcodeEventsListener() != null) {
                            getBarcodeEventsListener().callbackBarcodeData(UP_BARCODE_EVENTS_TYPE_GENERATION,UP_BARCODE_EVENTS_STATUS_SUCCESS, barcodeEntity);
                        }
//                        byte[] bmData = grdb.getBarcodeBuf();
//                        String contents = grdb.getContents();
//                        bundle.putString(BarcodeResultHandler.CB_BUNDLE_KEY_CONTENTS, contents);
//                        bundle.putByteArray(BarcodeResultHandler.CB_BUNDLE_KEY_BITMAP,bmData);
//
//                        LogUtils.d(TAG, " ---onActivityResult--- bmData = "+bmData.length +",contents = "+contents);
//                        Message msg = mHandler.obtainMessage();
//                        msg.what = BarcodeResultHandler.CB_HANDLER_MSG_TYPE_GENERATION;
//                        msg.obj = bundle;
//                        mHandler.sendMessage(msg);
                    } else {
                        if (getBarcodeEventsListener() != null) {
                            getBarcodeEventsListener().callbackBarcodeData(UP_BARCODE_EVENTS_TYPE_GENERATION,UP_BARCODE_EVENTS_STATUS_FAILED,null);
                        }
                    }

            } else {
                if (getBarcodeEventsListener() != null) {
                    getBarcodeEventsListener().callbackBarcodeData(UP_BARCODE_EVENTS_TYPE_GENERATION,UP_BARCODE_EVENTS_STATUS_CANCEL,null);
                }
            }
            break;
            
        }
        
    }


    protected void startScanBarcode(Context context) {
        LogUtils.d(TAG, "======= scan =======");
        Intent intent = new Intent(context,CaptureActivity.class);
        ((Activity)context).startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    protected void startGenerateBarcode(Context context,String srcContent) {
        Intent intent = new Intent(Intents.Encode.ACTION);
        //每次HOME 键回来都会从stack 中被清除
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
        intent.putExtra(Intents.Encode.DATA, srcContent);
        intent.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE.toString());
        ((Activity)context).startActivityForResult(intent, REQUEST_CODE_GENERATE);
    }
    
}
