package com.qrcode.framework.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.ByteArrayOutputStream;

public class Utils {

    public static byte[] BitmapToBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap BytesToBimap(byte[] data) {
        if (data.length != 0) {
            return BitmapFactory.decodeByteArray(data, 0, data.length);
        } else {
            return null;
        }
    }

    public static String getUserUniqueId(Context context) {
        String userUniqueId = null;
        try {
            final String UUID = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
            if (UUID == null || "".equals(UUID)) {
                userUniqueId = getMacAddress(context);
            } else {
                userUniqueId = UUID;
            }
        } catch(Exception e) {
            userUniqueId = getMacAddress(context);
        }
        if (userUniqueId == null || "".equals(userUniqueId)) {
            userUniqueId = getRandomNumber();
        }
        return userUniqueId;
    }

    public static String getMacAddress(Context context) {
        String macAddress = null;
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        if (info != null) {
            macAddress =  info.getMacAddress();
        }
        return macAddress;
    }

    public static String getRandomNumber() {
        StringBuffer strRandom = new StringBuffer();
        for(int i = 0;i< 10 ; i++) {
            double random = Math.random() * 100;
            strRandom.append(random);
        }
        return strRandom.toString();
    }


}
