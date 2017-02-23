package com.qrcode.framework;

import com.qrcode.framework.entity.BarcodeEntity;

/**
 * <p>
 * Noted:
 *  Necessary: permission "android.permission.CAMERA"
 *  </p>
 * Created by Andy.chen on 2017/2/22.
 */

public interface IBarcodeEventsListener {

    public enum UpBarcodeEventsType {
        UP_BARCODE_EVENTS_TYPE_SCAN,
        UP_BARCODE_EVENTS_TYPE_GENERATION
    }
    public enum UpBarcodeEventsStatus {
        UP_BARCODE_EVENTS_STATUS_SUCCESS,
        UP_BARCODE_EVENTS_STATUS_FAILED,
        UP_BARCODE_EVENTS_STATUS_CANCEL
    }

    /**
     *
     * @param type see enum UpBarcodeEventsType
     * @param status see enum UpBarcodeEventsStatus
     * @param entity see BarcodeEntity
     */
    public void callbackBarcodeData(UpBarcodeEventsType type, UpBarcodeEventsStatus status, BarcodeEntity entity);
}
