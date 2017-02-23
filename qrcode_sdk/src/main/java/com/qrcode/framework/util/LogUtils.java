/*
 * Copyright (C) 2010 The ubiLive Game Cloud Project
 */
 
package com.qrcode.framework.util;


import android.util.Log;

public class LogUtils {
	private static boolean mEnabled = true;
	
	private LogUtils() {
		
	}
	
	public static void enableLog(boolean enabled) {
		mEnabled = enabled;
	}
     public static int d(String tag, String msg) {
    	 int result = 0;
         if (mEnabled) {
         	result = Log.d(tag, "current Thread id = " + Thread.currentThread().getId() + ":" + msg);
         }
         return result;
    }

    public static int d(String tag, String msg, Throwable tr) {
    	int result = 0;
        if (mEnabled) {
        	result = Log.d(tag, "current Thread id = " + Thread.currentThread().getId() + ":" + msg, tr);
        }
        return result;
    }

}
