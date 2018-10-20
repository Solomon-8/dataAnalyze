package test;

import gps.GpsUtil;
import io.Data;
import io.MatrixWithTime;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Solomon
 * @date 2018/10/20
 * if you founded any bugs in my code
 * look at my face
 * that's a feature
 * ─ wow ──▌▒█───────────▄▀▒▌───
 * ────────▌▒▒▀▄───────▄▀▒▒▒▐───
 * ───────▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐───
 * ─────▄▄▀▒▒▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐───
 * ───▄▀▒▒▒▒▒▒ such difference ─
 * ──▐▒▒▒▄▄▄▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄▒▒▌──
 * ──▌▒▒▐▄█▀▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐──
 * ─▐▒▒▒▒▒▒▒▒▒▒▒▌██▀▒▒▒▒▒▒▒▒▀▄▌─
 * ─▌▒▀▄██▄▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌─
 * ─▌▀▐▄█▄█▌▄▒▀▒▒▒▒▒▒░░░░░░▒▒▒▐─
 * ▐▒▀▐▀▐▀▒▒▄▄▒▄▒▒▒ electrons ▒▌
 * ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒░░░░░░▒▒▒▐─
 * ─▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌─
 * ─▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐──
 * ──▀ amaze ▒▒▒▒▒▒▒▒▒▒▒▄▒▒▒▒▌──
 * ────▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀───
 * ───▐▀▒▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀─────
 * ──▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▀────────
 * " "
 */
public class DataTest {
    public static void main(String[] args) throws FileNotFoundException {
//        String path = "C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\email.txt";
//        FileInputStream fileInputStream = new FileInputStream(path);
        Data data = new Data();
        GpsUtil gpsUtil = new GpsUtil();
//        System.out.println(data.readAsMatrix(fileInputStream,String.class));
//        System.out.println("-----------------------------------------------------------------------");
//        System.out.println(data.readAsMatrixWithProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\email1.txt"),8,String.class));
//        System.out.println("-----------------------------------------------------------------------");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("> yyyy  M dd HH mm ss.0000000  0 SS");
//        System.out.println(data.readAsMatrixWithTime(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\test.13O"),">",simpleDateFormat,String.class));
//        System.out.println("-----------------------------------------------------------------------");
//        System.out.println(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\test.13O"),simpleDateFormat,">",3,String.class).size());
//        System.out.println(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\test.13O"),simpleDateFormat,">",3,String.class));
//        System.out.println(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\测绘数据-502392101a.13O"),simpleDateFormat,">",3,String.class));
//        System.out.println(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\test.13O"),simpleDateFormat,">",3,String.class),"G 1").size());
//        System.out.println(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\test.13O"),simpleDateFormat,">",3,String.class),"G 1"));
//        System.out.println(gpsUtil.calculateCycleSlipMatrix(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\test.13O"),simpleDateFormat,">",3,String.class),"G 1"),4,1));
//        gpsUtil.printMatrix(gpsUtil.calculateCycleSlipMatrix(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\test.13O"),simpleDateFormat,">",3,String.class),"G 1"),4,1),1);
        gpsUtil.printCycleSlipAndCountTimes(gpsUtil.calculateCycleSlipMatrix(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\测绘数据-502392101a.13O"),simpleDateFormat,">",3,String.class),"G 1"),4,1),1,true);
//        for (int i = 0; i < matrix.size() - 3; i++) {
//            MatrixWithTime<String> a = matrix.get(i);
//            MatrixWithTime<String> b = matrix.get(i+1);
//            MatrixWithTime<String> c = matrix.get(i+2);
//            MatrixWithTime<String> d = matrix.get(i+3);
//            Double a1 = Double.parseDouble(a.getData().get(0).get(1));
//            Double b1 = Double.parseDouble(b.getData().get(0).get(1));
//            Double c1 = Double.parseDouble(c.getData().get(0).get(1));
//            Double d1 = Double.parseDouble(d.getData().get(0).get(1));
//            if (Math.round(b1/a1) == -3 || Math.round(b1/a1) == -2 || Math.round(b1/a1) == -4){
//                if (Math.round(c1/a1) == 3 || Math.round(c1/a1) == 2 || Math.round(c1/a1) == 4){
//                    if (Math.round(d1/a1) == -1 || Math.round(d1/a1) == 0 || Math.round(d1/a1) == -2){
//                        System.out.println("!!!");
//                        System.out.println(a);
//                        System.out.println(b);
//                        System.out.println(c);
//                        System.out.println(d);
//                        System.out.println("!!!!!!");
//                        count++;
//                    }
//                }
//            }
////            if (Math.round(b1/a1) == -3){
////                if (Math.round(c1/a1) == 3){
////                    if (Math.round(d1/a1) == -1){
////                        System.out.println("!!!");
////                        System.out.println(a);
////                        System.out.println(b);
////                        System.out.println(c);
////                        System.out.println(d);
////                        System.out.println("!!!!!!");
////                        count++;
////                    }
////                }
////            }
//        }
//        System.out.println(count);

//        System.out.println(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\test.13O"),simpleDateFormat,">",3,String.class),"G 1").size());
//        System.out.println(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\test.13O"),simpleDateFormat,">",3,String.class),"G 1"));
//        System.out.println(gpsUtil.calculateCycleSlipMatrix(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\test.13O"),simpleDateFormat,">",3,String.class),"G 1"),4,1));
//        gpsUtil.printMatrix(gpsUtil.calculateCycleSlipMatrix(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\test.13O"),simpleDateFormat,">",3,String.class),"G 1"),4,1),1);
    }
}
