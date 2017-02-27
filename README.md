# 二维码SDK #
## 功能：  ##
1. 支持扫码，生成二维码
2. 支持自定义扫码框图案，【如默认仿支付宝的扫码框样式】
3. 支持生成二维码，接口直接返回二维码图片，及内容

## 接入方式： ##

请下载: qrcode_sdk-release.aar 然后放到项目libs下

**1. Build.gradle**

	`repositories {
    flatDir {
        dirs 'libs'
        dirs 'aars'
    }
	}`
	`dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile(name: 'qrcode_sdk-release', ext: 'aar')
	}`

**2 接口**

1.初始化

	`	private IBarcodeEngine mIBarcodeEngine;
	
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
        });`
2.启动扫码器

    `mIBarcodeEngine.scanBarcode(mContext);`

3.启动生成二维码

    mIBarcodeEngine.generateBarcode(mContext,CONTENT_GENERATE_BARCODE);


4.Activity 交互

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (mIBarcodeEngine != null) {
            mIBarcodeEngine.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

