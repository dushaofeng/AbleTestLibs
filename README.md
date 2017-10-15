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
