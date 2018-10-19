package io;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Solomon
 * @date 2018/10/19
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
public class Data implements ReadData{

    public static void main(String[] args) throws FileNotFoundException {
        Data stringData = new Data();
        List<List<String>> a = stringData.readAsMatrix(new FileInputStream("C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\email.txt"),String.class);
        System.out.println(a);
    }

    @Override
    public <T> List<List<T>> readAsMatrix(FileInputStream inputStream, Class<T> clazz) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<List<T>> returnResult = new ArrayList<>();
        try {
            String line = reader.readLine();
            while (line != null){
                String[] result = line.split(" ");
                List<T> list = new ArrayList<T>();
                for (String i : result){
                    if (StringUtils.isNotEmpty(i)){
                        try {
                            Constructor<T> constructor = clazz.getConstructor(String.class);
                            try {
                                T object = constructor.newInstance(i);
                                list.add(object);
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                                throw new RuntimeException("小可爱，你传入的类不能被实例化");
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                                throw new RuntimeException("小可爱，你可能不配set/get field或invoke method");
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                                //Ignore
                            }

                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                            throw new RuntimeException("没有找到参数为String的构造函数");
                        }
                    }
                }
                returnResult.add(list);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件时发生错误，请检查文件格式");
        }
        return returnResult;
    }

    @Override
    public <T> List<List<T>> readAsMatrixWithProtectFirstColumn(FileInputStream inputStream, int position, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> List<MatrixWithTime<T>> readAsMatrixWithTime(FileInputStream inputStream, String sign, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> List<MatrixWithTime<T>> readAsMatrixWithTimeProtectFirstColumn(FileInputStream inputStream, String sign, Class<T> clazz) {
        return null;
    }
}
