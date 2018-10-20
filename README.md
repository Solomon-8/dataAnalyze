# dataAnalyze
一个有关数据分析的项目，写它的初衷是因为GPS课程需要计算卫星周跳。

* 现在觉得它不配叫数据分析
* 原本的期望是做一个数据分析的工具，提供基本的数据读取，数据处理，再提供几个常用的分析方法
* 但是发现目前的需求没有这么大，而且个人时间似乎不够充裕，最主要的是没有搞清楚周跳怎么计算
* 似乎没有发现有人能非常肯定的告诉我周跳怎么个计算方式，而且无法验证计算出来的数据是否正确

* 所以关于数据分析的部分需要暂时往后推延一下，先解决周跳计算的问题

利用这个库来计算周跳只需要简单的几行代码即可
```java
public class GpsDemo {
    //需要用到的工具类
    static Data data = new Data();
    static GpsUtil gpsUtil = new GpsUtil();


    public static void main(String[] args) {
        //数据文件所在的位置
        String path = "C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\测绘数据-502392101a.13O";
        //代表时间那一行的时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("> yyyy  M dd HH mm ss.0000000  0 SS");
        //读取所期望的数据
        //比如这里就是读取了，代表时间那一行的标志为">" , 时间格式如上， 第一列表示卫星标号最多占3位, 以String格式返回, 卫星G1的所有数据
        List<MatrixWithTime<String>> gpsData = data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(path,simpleDateFormat,">",3,String.class),"G 1");
        //这里利用上面获取到的数据通过高次差法来计算卫星周跳，4次差，需要计算的数据在第二列(列号的编号从0开始)
        List<MatrixWithTime<String>> matrix = gpsUtil.calculateCycleSlipMatrix(gpsData,4,1);
        //这里是输出计算4次差以后，满足1:-3:3:-1的周跳点和周跳次数
        //最后的true代表严格程度，如果true就严格到四舍五入必须满足比例，false满足±1
        gpsUtil.printCycleSlipAndCountTimes(matrix,1,true);
    }
}
```


```java
以G1数据为基础，计算后的输出结果如下:

第1次周跳发生时刻:Fri Apr 12 11:58:00 CST 2013
MatrixWithTime{time=Fri Apr 12 11:58:00 CST 2013, data=[[G 1, 1.960999984294176, -16148630.75446, 39.000, -10920996.04746, 29.000, 24465685.406]]}
MatrixWithTime{time=Fri Apr 12 11:58:30 CST 2013, data=[[G 1, -5.013999991118908, -16256760.64847, 41.000, -11005252.49246, 28.000, 24445108.430]]}
MatrixWithTime{time=Fri Apr 12 11:59:00 CST 2013, data=[[G 1, 6.232999995350838, -16364941.16447, 42.000, -11089548.32446, 29.000, 24424521.672]]}
MatrixWithTime{time=Fri Apr 12 11:59:30 CST 2013, data=[[G 1, -2.9389999955892563, -16473164.37947, 42.000, -11173877.35946, 30.000, 24403927.359]]}
第1次周跳的四个点打印结束
第2次周跳发生时刻:Fri Apr 12 12:00:00 CST 2013
MatrixWithTime{time=Fri Apr 12 12:00:00 CST 2013, data=[[G 1, -0.9409999996423721, -16581426.96547, 42.000, -11258237.04746, 29.000, 24383324.961]]}
MatrixWithTime{time=Fri Apr 12 12:00:30 CST 2013, data=[[G 1, 2.697999991476536, -16689732.77047, 42.000, -11342630.35246, 29.000, 24362714.266]]}
MatrixWithTime{time=Fri Apr 12 12:01:00 CST 2013, data=[[G 1, -2.574999988079071, -20857.16047, 41.000, -11427050.59046, 29.000, 24342096.617]]}
MatrixWithTime{time=Fri Apr 12 12:01:30 CST 2013, data=[[G 1, 0.6879999898374081, -129235.66447, 41.000, -11511500.42646, 28.000, 24321471.836]]}
第2次周跳的四个点打印结束
第3次周跳发生时刻:Fri Apr 12 13:22:30 CST 2013
MatrixWithTime{time=Fri Apr 12 13:22:30 CST 2013, data=[[G 1, 8.076999992132187, -16089641.21149, 50.000, -7170875.69949, 42.000, 21284218.703]]}
MatrixWithTime{time=Fri Apr 12 13:23:00 CST 2013, data=[[G 1, -23.734999999403954, -16165530.41449, 50.000, -7230010.05149, 43.000, 21269777.625]]}
MatrixWithTime{time=Fri Apr 12 13:23:30 CST 2013, data=[[G 1, 23.94700000062585, -16240996.76649, 49.000, -7288814.90649, 43.000, 21255416.813]]}
MatrixWithTime{time=Fri Apr 12 13:24:00 CST 2013, data=[[G 1, -9.242999993264675, -16316038.84049, 50.000, -7347289.15249, 44.000, 21241136.703]]}
第3次周跳的四个点打印结束
第4次周跳发生时刻:Fri Apr 12 14:27:00 CST 2013
MatrixWithTime{time=Fri Apr 12 14:27:00 CST 2013, data=[[G 1, -14189.680999998003, -4851378.02349, 51.000, -11486935.77349, 48.000, 20230183.930]]}
MatrixWithTime{time=Fri Apr 12 14:27:30 CST 2013, data=[[G 1, 43546.37600000203, -4864241.99249, 51.000, -11496959.61349, 48.000, 20227735.992]]}
MatrixWithTime{time=Fri Apr 12 14:28:00 CST 2013, data=[[G 1, -44528.1430000104, -4876536.21949, 51.000, -11506539.50049, 48.000, 20225396.555]]}
MatrixWithTime{time=Fri Apr 12 14:28:30 CST 2013, data=[[G 1, 15178.136000018567, -4888257.66449, 51.000, -11515673.05549, 48.000, 20223165.984]]}
第4次周跳的四个点打印结束
第5次周跳发生时刻:Fri Apr 12 15:01:00 CST 2013
MatrixWithTime{time=Fri Apr 12 15:01:00 CST 2013, data=[[G 1, 174111.2919999957, -5000017.30949, 51.000, -11602757.89849, 49.000, 20201898.258]]}
MatrixWithTime{time=Fri Apr 12 15:01:30 CST 2013, data=[[G 1, -516569.0910000093, -5001469.69949, 51.000, -11603889.60249, 48.000, 20201621.828]]}
MatrixWithTime{time=Fri Apr 12 15:02:00 CST 2013, data=[[G 1, 510787.1290000193, -5002361.76249, 51.000, -11604584.69149, 48.000, 20201452.094]]}
MatrixWithTime{time=Fri Apr 12 15:02:30 CST 2013, data=[[G 1, -168329.72700002044, -5002688.83249, 51.000, -11604839.50849, 48.000, 20201390.031]]}
第5次周跳的四个点打印结束
-------------------------------------------------------------------------------------
总共发生的周跳次数:5

```

使用方式:

将[dataAnalyze-1.0-SNAPSHOT.jar](https://raw.githubusercontent.com/Solomon-8/dataAnalyze/master/src/main/resources/jars/dataAnalyze-1.0-SNAPSHOT.jar)添加到你的项目依赖中就可以使用了。

以IDEA为例，一种添加依赖的方式为：

通过Modules的Dependencies添加：(推荐)
1.打开 File -> Project Structure （Ctrl + Shift + Alt + S）

![打开File -> Project Structure图片](http://dl2.iteye.com/upload/attachment/0104/8876/694f8b5c-70ac-3fec-8e89-bde24aa42424.png)

2.单击 Modules -> Dependencies -> "+" -> "Jars or directories"

![打开modules图片](http://dl2.iteye.com/upload/attachment/0104/8878/03160851-defd-3cbb-9334-792f1b1f7795.png)


![添加jar图片](http://dl2.iteye.com/upload/attachment/0104/8880/d19fb3d1-0342-3e8c-bf1b-ec31006015cc.png)


3.选择硬盘上的jar包
4.Apply -> OK
