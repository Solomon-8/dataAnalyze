package main;

import gps.PseudorangeUtil;
import io.Data;
import io.MatrixWithTime;
import io.PseudorangeModule;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Point;
import model.PointWithErrorAndPesudorange;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static test.NewTest.*;

/**
 * @author Solomon
 * @since 2019/6/17
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
 */
public class Main {
    public static void main(String[] args) {
        PseudorangeUtil pseudorangeUtil = new PseudorangeUtil();
        Data data = new Data();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("> yyyy  M dd HH mm ss.0000000  0 SS");
        SimpleDateFormat simpleDateFormatb = new SimpleDateFormat("* yyyy  M dd HH mm ss.0000000");
        List<MatrixWithTime<String>> resultb = data.readAsMatrixWithTimeProtectFirstColumn("C:\\Users\\Solomon\\Desktop\\GPS\\igr_6.5.sp3", simpleDateFormatb, "*", 3, String.class);
//        List<MatrixWithTime<String>> result = data.readAsMatrixWithTimeProtectFirstColumn("C:\\Users\\Solomon\\Desktop\\GPS\\802140156k.19O", simpleDateFormat, ">", 3, String.class);
//        List<MatrixWithTime<String>> g16 = data.getDataByColumn(resultb, "G26", 0);
//
//        List<MatrixWithTime<String>> g26 = data.getDataByColumn(resultb, "G26", 0);
//        List<MatrixWithTime<String>> g18 = data.getDataByColumn(resultb, "G16", 0);
//        List<MatrixWithTime<String>> g08 = data.getDataByColumn(resultb, "G31", 0);
//        List<MatrixWithTime<String>> g07 = data.getDataByColumn(resultb, "G21", 0);
//        List<MatrixWithTime<String>> g14 = data.getDataByColumn(resultb, "G23", 0);
//        List<MatrixWithTime<String>> g23 = data.getDataByColumn(resultb, "G14", 0);
//
////        List<MatrixWithTime<String>> g26 = data.getDataByColumn(result, "G26", 0);
////        List<MatrixWithTime<String>> g21 = data.getDataByColumn(result, "G21", 0);
////        List<MatrixWithTime<String>> g31 = data.getDataByColumn(result, "G31", 0);
////        List<MatrixWithTime<String>> g23 = data.getDataByColumn(result, "G23", 0);
////        List<MatrixWithTime<String>> g29 = data.getDataByColumn(result, "G29", 0);
//        List<MatrixWithTime<String>> g16_15 = filterByTime(g16, 15);
//        List<List<MatrixWithTime<String>>> list = filterByTimeAndNames(resultb, g16_15.get(0).getTime(), "G26", "G16", "G31", "G21", "G23", "G14");
//        Map<String, Double> errMap = PseudorangeUtil.calcError(g16_15.get(0).getTime(), new String[]{"G26", "G16", "G31", "G21", "G23", "G14"}, g26, g18, g08, g07, g14, g23);
        Map<Date, PseudorangeModule> pseudorangeData = pseudorangeUtil.readDataFromFile("C:\\Users\\Solomon\\Desktop\\GPS\\chan1560.19o");
//        List<List<PointWithErrorAndPesudorange>> pointWithErrorAndPesudoranges = PseudorangeUtil.getPointPesudorange(list, pseudorangeData, null, "G26", "G16", "G31", "G21", "G23", "G14");
        List<List<PointWithErrorAndPesudorange>> pointWithErrorAndPesudoranges = new ArrayList<>();
        for (MatrixWithTime<String> matrix : resultb) {
            int count = 0;
            int useCount = 0;
            List<PointWithErrorAndPesudorange> tmp = new ArrayList<>();
            for (; useCount < Integer.valueOf(args[0]) && count < matrix.getData().size(); ) {
                Date time = matrix.getTime();
                List<String> a = matrix.getData().get(count);
                if (pseudorangeData.get(time).getPseudorange().get(a.get(0)) != null) {
                    try {
                        double aa = pseudorangeData.get(time).getPseudorange().get(a.get(0));
                        PointWithErrorAndPesudorange pointA = new PointWithErrorAndPesudorange(Double.parseDouble(a.get(1)), Double.parseDouble(a.get(2)), Double.parseDouble(a.get(3)), Double.parseDouble(a.get(4)), aa);
                        tmp.add(pointA);
                        useCount++;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("!!!");
                    }
                }
                count++;
            }
            pointWithErrorAndPesudoranges.add(tmp);
//            List<String> a = matrix.getData().get(0);
//            List<String> b = matrix.getData().get(1);
//            List<String> c = matrix.getData().get(2);
//            List<String> d = matrix.getData().get(3);
//            List<String> e = matrix.getData().get(4);
//            List<String> f =  matrix.getData().get(5);
//            double aa = pseudorangeData.get(time).getPseudorange().get(a.get(0));
//            double bb = pseudorangeData.get(time).getPseudorange().get(b.get(0));
//            double cc = pseudorangeData.get(time).getPseudorange().get(c.get(0));
//            double dd = pseudorangeData.get(time).getPseudorange().get(d.get(0));
//            double ee = pseudorangeData.get(time).getPseudorange().get(e.get(0));
//            double ff = pseudorangeData.get(time).getPseudorange().get(f.get(0));
//            PointWithErrorAndPesudorange pointA = new PointWithErrorAndPesudorange(Double.parseDouble(a.get(1)),Double.parseDouble(a.get(2)),Double.parseDouble(a.get(3)),Double.parseDouble(a.get(4)),aa);
//            PointWithErrorAndPesudorange pointB = new PointWithErrorAndPesudorange(Double.parseDouble(b.get(1)),Double.parseDouble(b.get(2)),Double.parseDouble(b.get(3)),Double.parseDouble(b.get(4)),bb);
//            PointWithErrorAndPesudorange pointC = new PointWithErrorAndPesudorange(Double.parseDouble(c.get(1)),Double.parseDouble(c.get(2)),Double.parseDouble(c.get(3)),Double.parseDouble(c.get(4)),cc);
//            PointWithErrorAndPesudorange pointD = new PointWithErrorAndPesudorange(Double.parseDouble(d.get(1)),Double.parseDouble(d.get(2)),Double.parseDouble(d.get(3)),Double.parseDouble(d.get(4)),dd);
//            PointWithErrorAndPesudorange pointE = new PointWithErrorAndPesudorange(Double.parseDouble(e.get(1)),Double.parseDouble(e.get(2)),Double.parseDouble(e.get(3)),Double.parseDouble(e.get(4)),ee);
//            PointWithErrorAndPesudorange pointF = new PointWithErrorAndPesudorange(Double.parseDouble(f.get(1)),Double.parseDouble(f.get(2)),Double.parseDouble(f.get(3)),Double.parseDouble(f.get(4)),ff);
        }



//        for (List<PointWithErrorAndPesudorange> ls : pointWithErrorAndPesudoranges) {
//
//            for (PointWithErrorAndPesudorange point : ls){
//                System.out.println(point);
//            }
//            System.out.println("======================================================");
//        }

//        for (List<PointWithErrorAndPesudorange> ls : pointWithErrorAndPesudoranges) {
//            for (PointWithErrorAndPesudorange point : ls) {
//                calcEarth(point);
//            }
//        }

        for (List<PointWithErrorAndPesudorange> ls : pointWithErrorAndPesudoranges) {
            Point point1 = pseudorangeUtil.calcPointPositionByPseudorange(ls);
//            Point3D point3D = new Point3D(point1.getX(), point1.getY(), point1.getZ());
            Main_main.result.add(calc(point1));
            System.out.println(calc(point1));
        }


//        for (List<PointWithErrorAndPesudorange> list1 : pointWithErrorAndPesudoranges) {
//            for (PointWithErrorAndPesudorange point : list1) {
//                System.out.println(point);
//            }
//            System.out.println("=============================================================");
//        }
//        System.out.println(pointWithErrorAndPesudoranges);
    }

    public static double calc(Point point) {
        return Math.sqrt(Math.pow(point.getX() + 2.67442764005491e+06, 2) + Math.pow(point.getY() - 3.75714307614075e+06, 2) + Math.pow(point.getZ() - 4.39152153176091e+06, 2));
    }

    public static void calcEarth(PointWithErrorAndPesudorange pointWithErrorAndPesudorange) {
        double w = 7.292115e-05;
        double t = 0.067;
        double wt = w * t;
        double need;
        double dx = wt * pointWithErrorAndPesudorange.getX() * 10e2;
        double dy = -wt * pointWithErrorAndPesudorange.getY() * 10e2;
        double p = pointWithErrorAndPesudorange.getPesudorange();
        need = ((pointWithErrorAndPesudorange.getX()+2.67442764005491e+06) * dx + (pointWithErrorAndPesudorange.getY()-3.75714307614075e+06) * dy) / p;
        pointWithErrorAndPesudorange.setPesudorange(pointWithErrorAndPesudorange.getPesudorange() - need);
    }

}
