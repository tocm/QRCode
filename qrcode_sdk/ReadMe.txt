https://github.com/zxing/zxing/releases
https://github.com/zxing/zxing/wiki/Getting-Started-Developing
https://github.com/zxing/zxing


一、先看看源码结构：
官方github：https://github.com/zxing/zxing
下载到本地： git clone https://github.com/zxing/zxing.git
项目挺多的，但是对我们有用的只有这3个项目。

github上有这三个项目的官方解释：
android-core: Android-related code shared among android, androidtest, glass，PS 我的翻译，这里面只有一个文件，应该是工具之类的吧。反正这个是必须的
android: Android client Barcode Scanner ，PS，我的翻译，就是barcode scaner的程序源码。
core: The core image decoding library, and test code，PS，必须的类库源码，需要提前把这个项目编译成core.jar，我们可以自己编译，也可以用别人打包好的。

2. 添加core.jar 类库
core.jar这个类库，可以自己编译，也可以下载别人编译好的。
这里为了方便，先介绍如何导入别人编译好的core.jar

下载地址：http://repo1.maven.org/maven2/com/google/zxing/
http://repo1.maven.org/maven2/com/google/zxing/core/3.2.1/core-3.2.1.jar
下载完成之后，把这个文件copy到AS 项目中的app\libs目录下，

3. 添加android-core
把这个项目下CameraConfigurationUtils.java 复制到app\src\main\java\com\google\zxing\client\android\camera 下
或者 直接引入 android-core-xxx.jar
