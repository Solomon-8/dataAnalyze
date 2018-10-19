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
                Double count = Double.parseDouble(String.valueOf(matrixWithTimes.get(j).getData().get(0).get(position))) - Double.parseDouble(String.valueOf(matrixWithTimes.get(j-1).getData().get(0).get(position)));
                try {
                    countData.get(0).set(position, (T) count);
                    countResult.setData(countData);
                } catch (ClassCastException e){
                    e.printStackTrace();
                    throw new RuntimeException("计算周跳对应的数据类型应该是Double,实际传入的类型无法cast to Double");
                }
                returnResult.set(j-1,countResult);
            }
            returnResult.remove(times);
        }
        return returnResult;
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
            System.out.println(matrix.getData().get(0).get(position).toString());
        }
    }

}
