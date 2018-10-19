package io;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            String line;
            while ((line = reader.readLine()) != null){
                String[] result = line.split(" ");
                List<T> list = new ArrayList<T>();
                for (String i : result){
                    if (StringUtils.isNotEmpty(i)){
                        list.add(newInstance(clazz,i));
                    }
                }
                returnResult.add(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件时发生错误，请检查文件格式");
        }
        return returnResult;
    }

    @Override
    public <T> List<List<T>> readAsMatrixWithProtectFirstColumn(FileInputStream inputStream, int position, Class<T> clazz) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<List<T>> returnResult = new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null){
                returnResult.add(readProtectFirstColumn(line,position,clazz));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件时发生错误，请检查文件格式");
        }
        return returnResult;
    }

    @Override
    public <T> List<MatrixWithTime<T>> readAsMatrixWithTime(FileInputStream inputStream, String sign, SimpleDateFormat format, Class<T> clazz) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<MatrixWithTime<T>> returnResult = new ArrayList<>();
        List<List<T>> data = new ArrayList<>();
        try {
            String line;
            MatrixWithTime<T> matrixWithTime = null;
            while ((line = reader.readLine()) != null){
                if (line.contains(sign)){
                    //这一行包含sign，代表这一行代表着时间
                    if (matrixWithTime != null){
                        /*
                          不为空代表被初始化过，所以应该把data加入到这个对象里面
                          并且把这个对象添加到returnResult里面
                         */
                        matrixWithTime.setData(data);
                        returnResult.add(matrixWithTime);
                        data = new ArrayList<>();
                    }
                    matrixWithTime = new MatrixWithTime<>();
                    matrixWithTime.setTime(parseDate(line,format));
                } else {
                    String[] result = line.split(" ");
                    List<T> list = new ArrayList<T>();
                    for (String i : result){
                        if (StringUtils.isNotEmpty(i)){
                            list.add(newInstance(clazz,i));
                        }
                    }
                    data.add(list);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件时发生错误，请检查文件格式");
        }
        return returnResult;
    }

    @Override
    public <T> List<MatrixWithTime<T>> readAsMatrixWithTimeProtectFirstColumn(FileInputStream inputStream, SimpleDateFormat format, String sign, int position, Class<T> clazz) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<MatrixWithTime<T>> returnResult = new ArrayList<>();
        List<List<T>> data = new ArrayList<>();
        try {
            String line;
            MatrixWithTime<T> matrixWithTime = null;
            while ((line = reader.readLine()) != null){
                if (line.contains(sign)){
                    //这一行包含sign，代表这一行代表着时间
                    if (matrixWithTime != null){
                        /*
                          不为空代表被初始化过，所以应该把data加入到这个对象里面
                          并且把这个对象添加到returnResult里面
                         */
                        matrixWithTime.setData(data);
                        returnResult.add(matrixWithTime);
                        data = new ArrayList<>();
                    }
                    matrixWithTime = new MatrixWithTime<>();
                    matrixWithTime.setTime(parseDate(line,format));
                } else {
                    data.add(readProtectFirstColumn(line,position,clazz));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件时发生错误，请检查文件格式");
        }
        return returnResult;
    }


    private <T> T newInstance(Class<T> clazz,String parameter){
        try {
            Constructor<T> constructor = clazz.getConstructor(String.class);
            try {
                return constructor.newInstance(parameter);
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new RuntimeException("小可爱，你传入的类不能被实例化");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("小可爱，你可能不配set/get field或invoke method");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new RuntimeException("小可爱，你好像触发了一个预期不会发生的异常，快告诉我是怎么触发的吧");
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("没有找到参数为String的构造函数");
        }
    }

    private <T> List<T> readProtectFirstColumn(String line,int position,Class<T> clazz){
        List<T> list = new ArrayList<T>();
        String fistColumn = line.substring(0,position);
        list.add(newInstance(clazz,fistColumn));
        line = line.substring(position);
        String[] result = line.split(" ");
        for (String i : result){
            if (StringUtils.isNotEmpty(i)){
                list.add(newInstance(clazz,i));
            }
        }
        return list;
    }

    private Date parseDate(String line,SimpleDateFormat format){
        Date date;
        try {
            date = format.parse(line);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("format出错，出错的这行是:{" + line +"}");
        }
    }
}
