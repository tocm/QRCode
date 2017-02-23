package com.andydemo.qrcode;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qrcode.framework.BarcodeEngineImpl;
import com.qrcode.framework.IBarcodeEngine;
import com.qrcode.framework.IBarcodeEventsListener;
import com.qrcode.framework.entity.BarcodeEntity;
import com.qrcode.framework.util.Utils;

import static com.qrcode.framework.IBarcodeEventsListener.UpBarcodeEventsStatus.UP_BARCODE_EVENTS_STATUS_SUCCESS;
import static com.qrcode.framework.IBarcodeEventsListener.UpBarcodeEventsType.UP_BARCODE_EVENTS_TYPE_GENERATION;
import static com.qrcode.framework.IBarcodeEventsListener.UpBarcodeEventsType.UP_BARCODE_EVENTS_TYPE_SCAN;


public class MainActivity extends AppCompatActivity {

    private IBarcodeEngine mIBarcodeEngine;
    private Context mContext;
    private final static String CONTENT_GENERATE_BARCODE = "https://github.com/tocm";

    private TextView mTxBarcodeContent;
    private ImageView mImgBarcode;
    private Button mBtnTestScan,mBtnTestGenerate;
    private boolean mEnablePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //test case
        mTxBarcodeContent = (TextView) this.findViewById(com.google.zxing.client.android.R.id.contents);
        mImgBarcode = (ImageView) this.findViewById(com.google.zxing.client.android.R.id.img_barcode);
        mBtnTestScan = (Button) this.findViewById(R.id.btn_scan);
        mBtnTestScan.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                mIBarcodeEngine.scanBarcode(mContext);

            }});
        mBtnTestGenerate = (Button) this.findViewById(R.id.btn_generate);
        mBtnTestGenerate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                mIBarcodeEngine.generateBarcode(mContext,CONTENT_GENERATE_BARCODE);
            }});

        mIBarcodeEngine = new BarcodeEngineImpl();
        mIBarcodeEngine.setBarcodeEventsListener(new IBarcodeEventsListener() {
            @Override
            public void callbackBarcodeData(UpBarcodeEventsType upBarcodeEventsType, UpBarcodeEventsStatus upBarcodeEventsStatus, BarcodeEntity barcodeEntity) {

                if (upBarcodeEventsType == UP_BARCODE_EVENTS_TYPE_SCAN) {
                    if (upBarcodeEventsStatus == UP_BARCODE_EVENTS_STATUS_SUCCESS) {
                        mTxBarcodeContent.setText(barcodeEntity.getContents());
                        mImgBarcode.setImageBitmap(Utils.BytesToBimap(barcodeEntity.getBarcodeBuf()));
                    } else {
                        Snackbar.make(mBtnTestScan, R.string.scan_barcode_failed, Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                } else if (upBarcodeEventsType == UP_BARCODE_EVENTS_TYPE_GENERATION) {
                    if (upBarcodeEventsStatus == UP_BARCODE_EVENTS_STATUS_SUCCESS) {
                        mTxBarcodeContent.setText(barcodeEntity.getContents());
                        mImgBarcode.setImageBitmap(Utils.BytesToBimap(barcodeEntity.getBarcodeBuf()));
                    } else {
                        Snackbar.make(mBtnTestGenerate, R.string.generate_barcode_failed, Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (mIBarcodeEngine != null) {
            mIBarcodeEngine.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private final static int CODE_FOR_CAMERA_PERMISSION = 1;
    /**
     * 针对Android M 系统特性添加对权限的受限管理
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CODE_FOR_CAMERA_PERMISSION){
            if (permissions[0].equals(Manifest.permission.CAMERA)
                    &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //用户同意使用
                mEnablePermission = true;
            }else{
                //用户不同意，自行处理即可
                finish();
            }
        }
    }


    /**
     * 针对Android M 系统开始，对权限的管理限制
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        int hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA);
        if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    CODE_FOR_CAMERA_PERMISSION);
            return;
        } else {
            //用户同意使用
            mEnablePermission = true;
        }
    }
}
