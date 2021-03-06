package gps;

import io.MatrixWithTime;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

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
public class GpsUtil implements Gps {
    @Override
    public <T> List<MatrixWithTime<T>> calculateCycleSlipMatrix(List<MatrixWithTime<T>> matrixWithTimes, int time, int position) {
        int size = matrixWithTimes.size();
        if (time > size - 1){
            throw new RuntimeException("小老弟，你的数据不配计算这么高的差次。");
        }
        List<MatrixWithTime<T>> returnResult = deepCopy(matrixWithTimes);

        for (int i = 0; i < time; i++) {
            int times = returnResult.size() - 1;
            for (int j = times; j > 0; j--) {
                MatrixWithTime<T> countResult = returnResult.get(j-1);
                List<List<T>> countData = countResult.getData();
                String count = String.valueOf(Double.parseDouble(String.valueOf(matrixWithTimes.get(j).getData().get(0).get(position))) - Double.parseDouble(String.valueOf(matrixWithTimes.get(j-1).getData().get(0).get(position))));
                try {
                    countData.get(0).set(position, (T) count);
                    countResult.setData(countData);
                    countResult.setTime(matrixWithTimes.get(j).getTime());
                } catch (ClassCastException e){
                    e.printStackTrace();
                    throw new RuntimeException("计算周跳对应的数据类型应该是Double,实际传入的类型无法cast to Double");
                }
                returnResult.set(j-1,countResult);
            }
            returnResult.remove(times);
            matrixWithTimes = deepCopy(returnResult);
        }
        return returnResult;
    }

    @Override
    public void printCycleSlipAndCountTimes(List<MatrixWithTime<String>> matrixWithTimes, int position, boolean strict) {
        int count = 1;
        if (strict){
            for (int i = 0; i < matrixWithTimes.size() - 3; i++) {
                MatrixWithTime<String> a = matrixWithTimes.get(i);
                MatrixWithTime<String> b = matrixWithTimes.get(i+1);
                MatrixWithTime<String> c = matrixWithTimes.get(i+2);
                MatrixWithTime<String> d = matrixWithTimes.get(i+3);
                Double a1 = Double.parseDouble(a.getData().get(0).get(position));
                Double b1 = Double.parseDouble(b.getData().get(0).get(position));
                Double c1 = Double.parseDouble(c.getData().get(0).get(position));
                Double d1 = Double.parseDouble(d.getData().get(0).get(position));
                if (Math.round(b1/a1) == -3){
                    if (Math.round(c1/a1) == 3){
                        if (Math.round(d1/a1) == -1){
                            count = getCount(count, a, b, c, d, b1, c1, 1, b1 / d1, c1 / d1, 3, d1);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < matrixWithTimes.size() - 3; i++) {
                MatrixWithTime<String> a = matrixWithTimes.get(i);
                MatrixWithTime<String> b = matrixWithTimes.get(i+1);
                MatrixWithTime<String> c = matrixWithTimes.get(i+2);
                MatrixWithTime<String> d = matrixWithTimes.get(i+3);
                Double a1 = Double.parseDouble(a.getData().get(0).get(position));
                Double b1 = Double.parseDouble(b.getData().get(0).get(position));
                Double c1 = Double.parseDouble(c.getData().get(0).get(position));
                Double d1 = Double.parseDouble(d.getData().get(0).get(position));
                count = getCount(count, a, b, c, d, a1, b1, 3, c1/a1, d1/a1, 1, d1);
            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("总共发生的周跳次数:" + --count);
    }

    private int getCount(int count, MatrixWithTime<String> a, MatrixWithTime<String> b, MatrixWithTime<String> c, MatrixWithTime<String> d, Double b1, Double c1, int i2, double v, double v2, int i3, Double d1) {
        if (Math.round(c1/b1) == -i2){
            if (Math.round(v) == 3){
                if (Math.round(v2) == -i3){
                    printCycleSlipPoint(a,b,c,d,count);
                    count++;
                }
            }
        }
        return count;
    }


    public static <T> List<T> deepCopy(List<T> src) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            @SuppressWarnings("unchecked")
            List<T> dest = (List<T>) in.readObject();
            return dest;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("小老弟，在进行深拷贝的过程中出错了！");
        }
    }

    public <T> void printMatrix(List<MatrixWithTime<T>> matrixWithTimes,int position){
        for (MatrixWithTime<T> matrix :  matrixWithTimes){
            System.out.println(matrix.getTime() + "  " +matrix.getData().get(0).get(position).toString());
        }
    }

    private void printCycleSlipPoint(MatrixWithTime a,MatrixWithTime b,MatrixWithTime c,MatrixWithTime d,int count){
        System.out.println("第"+ count +"次周跳发生时刻:" + a.getTime());
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println("第" + count + "次周跳的四个点打印结束");
    }

}
