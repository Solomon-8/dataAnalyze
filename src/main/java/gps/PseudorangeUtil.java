package gps;

import io.Data;
import io.MatrixWithTime;
import io.PseudorangeModule;
import model.Point;
import model.PointE;
import model.PointWithErrorAndPesudorange;
import org.apache.commons.lang3.StringUtils;
import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
public class PseudorangeUtil implements Pseudorange {

    private static final Double C = 299792458.0;

    @Override
    public double calcRj(double xi, double xui, double yi, double yui, double zi, double zui) {
        return Math.sqrt(Math.pow((xi - xui), 2) + Math.pow((yi - yui), 2) + Math.pow((zi - zui), 2));
    }

    @Override
    public double[][] calcGu(List<PointE> data) {
        double[][] result = new double[data.size()][4];
        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i).getEx();
            result[i][1] = data.get(i).getEy();
            result[i][2] = data.get(i).getEz();
            result[i][3] = 1;
        }
        return result;
    }

    public double[][] calcAData(List<Double[]> data) {
        double[][] result = new double[data.size()][data.size() * 4];
        for (int i = 0; i < data.size(); i++) {
            result[i][4 * i] = data.get(i)[0];
            result[i][4 * i + 1] = data.get(i)[1];
            result[i][4 * i + 2] = data.get(i)[2];
            result[i][4 * i + 3] = data.get(i)[3];
        }

        return result;
    }

    @Override
    public Double[] calcEj(double ex, double ey, double ez, double delta) {
        return new Double[]{ex, ey, ez, delta};
    }

    @Override
    public Point calcPointPositionByPseudorange(List<PointWithErrorAndPesudorange> data) {
        for (PointWithErrorAndPesudorange point : data) {
            point.setX(point.getX() * 1000);
            point.setY(point.getY() * 1000);
            point.setZ(point.getZ() * 1000);
        }
        // 需要计算的点的个数
        List<Double> xu = new ArrayList<>();
        List<Double> yu = new ArrayList<>();
        List<Double> zu = new ArrayList<>();
        List<Double> tu = new ArrayList<>();
        xu.add(0.0);
        yu.add(0.0);
        zu.add(0.0);
        tu.add(0.0);
        xu.add(0.0);
        yu.add(0.0);
        zu.add(0.0);
        tu.add(0.0);
        List<Double> bj = new ArrayList<>();
        for (PointWithErrorAndPesudorange point : data) {
            bj.add(point.getError() * C * 1e-6);
        }
        List<Double> p = new ArrayList<>();
        for (PointWithErrorAndPesudorange point : data) {
            p.add(point.getPesudorange());
        }
        List<Double> sData = new ArrayList<>();
        for (int i = 0; i < bj.size(); i++) {
            sData.add(data.get(i).getX());
            sData.add(data.get(i).getY());
            sData.add(data.get(i).getZ());
            sData.add(bj.get(i));
        }
        Double[] sRealData = new Double[bj.size() * 4];
        sData.toArray(sRealData);
        Double[] pData = new Double[p.size()];
        p.toArray(pData);
        Matrix pMatrix = DenseMatrix.Factory.importFromArray(pData).transpose();
        Matrix sMatrix = DenseMatrix.Factory.importFromArray(sRealData).transpose();
        for (int i = 1; i <= 100; i++) {
            List<Double> rj = new ArrayList<>();
            for (PointWithErrorAndPesudorange point : data) {
                double Rj = calcRj(point.getX(), xu.get(i), point.getY(), yu.get(i), point.getZ(), zu.get(i));
                rj.add(Rj);
            }
            List<PointE> e = new ArrayList<>();
            for (int j = 0; j < rj.size(); j++) {
                double ex = (data.get(j).getX() - xu.get(i)) / rj.get(j);
                double ey = (data.get(j).getY() - yu.get(i)) / rj.get(j);
                double ez = (data.get(j).getZ() - zu.get(i)) / rj.get(j);
                PointE pointE = new PointE(ex, ey, ez);
                e.add(pointE);
            }
            List<Double[]> E = new ArrayList<>();
            for (PointE pointE : e) {
                Double[] eResult = calcEj(pointE.getEx(), pointE.getEy(), pointE.getEz(), -1);
                E.add(eResult);
            }
            double[][] aData = calcAData(E);

            double[][] GuResult = calcGu(e);
            Matrix GuMatrix = DenseMatrix.Factory.importFromArray(GuResult);
            Matrix AMatrix = DenseMatrix.Factory.importFromArray(aData);

            Matrix Dx = GuMatrix.transpose().mtimes(GuMatrix).pinv().mtimes(GuMatrix.transpose()).mtimes((AMatrix.mtimes(sMatrix).minus(pMatrix)));
            xu.add(Dx.getAsDouble(0, 0));
            yu.add(Dx.getAsDouble(1, 0));
            zu.add(Dx.getAsDouble(2, 0));
            tu.add(Dx.getAsDouble(3, 0));
        }
        return new Point(xu.get(xu.size() - 1), yu.get(yu.size() - 1), zu.get(zu.size() - 1));
    }

    @Override
    public Map<Date, PseudorangeModule> readDataFromFile(String path) {
        Map<Date, PseudorangeModule> result = new HashMap<>();
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("小可爱，没有找到你的文件，确认你的路径是否正确");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy MM dd HH mm ss");
        try {
            String line = reader.readLine();
            while (line != null) {
                int count;
                if (line.startsWith(" 19")) {
                    String[] data = line.split(" ");
                    List<String> tmp = new ArrayList<>();
                    for (String s : data) {
                        if (StringUtils.isNotBlank(s)) {
                            tmp.add(s);
                        }
                    }
                    PseudorangeModule pseudorangeModule = new PseudorangeModule();
                    int index = tmp.get(7).indexOf("G");
                    count = Integer.parseInt(tmp.get(7).substring(0, index));
                    String[] gpsData = tmp.get(7).split("G");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(tmp.get(0)).append(" ");
                    stringBuilder.append(tmp.get(1)).append(" ");
                    stringBuilder.append(tmp.get(2)).append(" ");
                    stringBuilder.append(tmp.get(3)).append(" ");
                    stringBuilder.append(tmp.get(4)).append(" ");
                    stringBuilder.append(tmp.get(5)).append(" ");
                    stringBuilder.append(tmp.get(6)).append(" ");
                    Date date = Data.parseDate(stringBuilder.toString(), simpleDateFormat);
                    pseudorangeModule.setDate(date);

                    Map<String, Double> a = new HashMap<>(48);
                    for (int i = 0; i < count; i++) {
                        line = reader.readLine();
                        String[] pseudorange = line.split(" ");
                        List<String> pseudorangeData = new ArrayList<>();
                        for (String s : pseudorange) {
                            if (StringUtils.isNotBlank(s)) {
                                pseudorangeData.add(s);
                            }
                        }
                        if (pseudorangeData.size() >= 4) {
                            a.put("G" + gpsData[i + 1], Double.valueOf(pseudorangeData.get(3)));
                        }
                        reader.readLine();
                    }
                    pseudorangeModule.setPseudorange(a);
                    result.put(date, pseudorangeModule);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件时发生错误，请检查文件格式");
        }
        return result;
    }

    public static List<List<PointWithErrorAndPesudorange>> getPointPesudorange(List<List<MatrixWithTime<String>>> data, Map<Date, PseudorangeModule> pseudorangeModuleMap, Map<String, Double> errorMap, String... name) {
        Object[] test = pseudorangeModuleMap.keySet().toArray();
        Arrays.sort(test);
        List<List<PointWithErrorAndPesudorange>> result = new ArrayList<>();
        for (List<MatrixWithTime<String>> m1 : data) {
            for (MatrixWithTime<String> m : m1) {
                m.setTime(new Date(m.getTime().getTime() / 1000 * 1000));
            }

        }
        int count = 0;
        for (int i = 0; i < 96; i++) {
            List<PointWithErrorAndPesudorange> tmpresult = new ArrayList<>();
            for (int j = 0; j < name.length; j++) {
                List<MatrixWithTime<String>> m1 = data.get(j);
                double x = Double.parseDouble(m1.get(count).getData().get(0).get(1));
                double y = Double.parseDouble(m1.get(count).getData().get(0).get(2));
                double z = Double.parseDouble(m1.get(count).getData().get(0).get(3));
                double t = Double.parseDouble(m1.get(count).getData().get(0).get(4));
//            double t = errorMap.get(name[i]);
                PointWithErrorAndPesudorange point;
                if (pseudorangeModuleMap.get(m1.get(i).getTime()).getPseudorange().get(name[j]) != null) {
                    point = new PointWithErrorAndPesudorange(x, y, z, t, pseudorangeModuleMap.get(m1.get(i).getTime()).getPseudorange().get(name[j]));
                } else {
                    point = new PointWithErrorAndPesudorange(x, y, z, t, 0.0);
                }
                tmpresult.add(point);
            }
            count++;
            result.add(tmpresult);
        }
        return result;
    }

    public static Map<String, Double> calcError(Date time, String[] name, List<MatrixWithTime<String>>... matrix) {
        Map<String, Double> result = new HashMap<>();
        for (String s : name) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].size(); j++) {
                    if ((matrix[i].get(j).getTime().getTime()) == time.getTime() / 1000 * 1000) {
                        if (matrix[i].get(j).getData().get(0).get(0).equals(s)) {
                            result.put(matrix[i].get(j).getData().get(0).get(0), Double.valueOf(matrix[i].get(j).getData().get(0).get(4)));
                        }
                    }
                }
            }
        }
        return result;
    }
}
