# AbleTestLibs
Able实验室，用于测试新控件、框架、想法等

#Kotlin
采用Kotlin开发，Studio创建工程时选择Kotlin支持即可，然后添加插件用于自动导入布局文件：
>apply plugin: 'kotlin-android-extensions'

#RadarView
雷达图，用于多维展示，可以自定义维度

#贝塞尔Path
https://github.com/GcsSloop/AndroidNote/blob/master/CustomView/Advance/%5B06%5DPath_Bezier.md

#Bezier
二阶贝塞尔View绘制测试

#Bezier3
三阶贝塞尔View绘制测试

#BezierMagicCircle
利用三阶贝塞尔绘制滚动的圆圈

#AlgorithmActivity
测试各种算法的效率

#SenseyTestActivity
添加Sensey的测试Activity，用于测试[sensey](https://github.com/nisrulz/sensey)的lib

##初始化方法
Sensey.getInstance().init(this,Sensey.SAMPLING_PERIOD_GAME);
第二个参数为传感器的灵敏度，默认为Normal，可选值为:
public static final int SAMPLING_PERIOD_FASTEST = 0;
public static final int SAMPLING_PERIOD_GAME = 1;
public static final int SAMPLING_PERIOD_NORMAL = 3;
public static final int SAMPLING_PERIOD_UI = 2;

##传感器使用

###摇晃传感器(startShakeDetection)
有三个参数分别介绍

####threshold
代表探测灵敏度，默认值3F，据测试，摇晃厉害可以达到60
计算方法，当晃动时，计算x^2+y^2+z^2然后开平方根，再减去9.8的地球默认加速度，然后加上上一次探测数据的0.9倍，累加之后与用户设置的threshold对比，大于阈值就出发探测器
####timeBeforeDeclaringShakeStopped
这个比较好理解，就是当摇晃停止后，延时多久出发停止的回掉函数，比如已经停止2秒后出发，或者3秒后触发，默认1秒
####第三个参数为回掉函数，无需解释


#EasyPermissionTestActivity

权限测试
