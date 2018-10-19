package test;

import gps.GpsUtil;
import io.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

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
        System.out.println(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\测绘数据-502392101a.13O"),simpleDateFormat,">",3,String.class),"G28").size());
        System.out.println(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\测绘数据-502392101a.13O"),simpleDateFormat,">",3,String.class),"G28"));
        System.out.println(gpsUtil.calculateCycleSlipMatrix(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\测绘数据-502392101a.13O"),simpleDateFormat,">",3,String.class),"G28"),4,1));
        gpsUtil.printMatrix(gpsUtil.calculateCycleSlipMatrix(data.getDataByFirstColumn(data.readAsMatrixWithTimeProtectFirstColumn(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\2018卫星导航系统第一次作业-周跳数据\\测绘数据-502392101a.13O"),simpleDateFormat,">",3,String.class),"G28"),4,1),1);
    }
}
