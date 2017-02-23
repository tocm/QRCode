package com.qrcode.framework.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 二维码数据实体类
 */
public class BarcodeEntity implements Parcelable  {

    private final static String TAG = BarcodeEntity.class.getSimpleName();
    public final static String CB_RESULT_KEY = "com.barcode.framework.MESSAGE.SCAN";

    //共用属性
    private String contents;
    private byte[] barcodeBuf;
    //以下是仅用于 scan barcode
    private String format;
    private String type;
    private String createTime;
    private String label;

    
    public BarcodeEntity() {}
    
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
      
    
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(format);
        dest.writeString(type);
        dest.writeString(createTime);
        dest.writeString(label);
        dest.writeString(contents);
        dest.writeInt(this.barcodeBuf.length);
        dest.writeByteArray(barcodeBuf);
//        bitmap.writeToParcel(dest, flags);  
    }
    
    public byte[] getBarcodeBuf() {
        return barcodeBuf;
    }

    public void setBarcodeBuf(byte[] barcodeBuf) {
        this.barcodeBuf = barcodeBuf;
    }

    public static final Parcelable.Creator<BarcodeEntity> CREATOR = new Creator<BarcodeEntity>() {
        
        @Override  
        public BarcodeEntity[] newArray(int size) {
            // TODO Auto-generated method stub  
            return new BarcodeEntity[size];
        }  
          
        @Override  
        public BarcodeEntity createFromParcel(Parcel in) {
            // TODO Auto-generated method stub  
            return new BarcodeEntity(in);
        }  
    };  
    
    public BarcodeEntity(Parcel in) {
        this.format = in.readString();
        this.type = in.readString();
        this.createTime = in.readString();
        this.label = in.readString();
        this.contents = in.readString();
        this.barcodeBuf = new byte[in.readInt()];
        in.readByteArray(barcodeBuf);
//        RDP.setBitmap(Bitmap.CREATOR.createFromParcel(in));  
    }
}
