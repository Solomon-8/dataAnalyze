package io;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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


    @Override
    public <T> List<List<T>> readAsMatrix(String path, Class<T> clazz) {
        try {
            FileInputStream inputStream = new FileInputStream(path);
            return readAsMatrix(inputStream,clazz);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("小可爱，没有找到你的文件，确认你的路径是否正确");
        }
    }

    @Override
    public <T> List<List<T>> readAsMatrixWithProtectFirstColumn(String path, int position, Class<T> clazz) {
        try {
            FileInputStream inputStream = new FileInputStream(path);
            return readAsMatrixWithProtectFirstColumn(inputStream,position,clazz);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("小可爱，没有找到你的文件，确认你的路径是否正确");
        }
    }

    @Override
    public <T> List<MatrixWithTime<T>> readAsMatrixWithTime(String path, String sign, SimpleDateFormat format, Class<T> clazz) {
        try {
            FileInputStream inputStream = new FileInputStream(path);
            return readAsMatrixWithTime(inputStream,sign,format,clazz);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("小可爱，没有找到你的文件，确认你的路径是否正确");
        }
    }

    @Override
    public <T> List<MatrixWithTime<T>> readAsMatrixWithTimeProtectFirstColumn(String path, SimpleDateFormat format, String sign, int position, Class<T> clazz) {
        try {
            FileInputStream inputStream = new FileInputStream(path);
            return readAsMatrixWithTimeProtectFirstColumn(inputStream,format,sign,position,clazz);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("小可爱，没有找到你的文件，确认你的路径是否正确");
        }
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
            String line = reader.readLine();
            MatrixWithTime<T> matrixWithTime = null;
            while (true){
                if (line == null){
                    if (matrixWithTime != null){
                        matrixWithTime.setData(data);
                        returnResult.add(matrixWithTime);
                    }
                    break;
                }
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
                line = reader.readLine();
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
            String line = reader.readLine();
            MatrixWithTime<T> matrixWithTime = null;
            while (true){
                if (line == null){
                    if (matrixWithTime != null){
                        matrixWithTime.setData(data);
                        returnResult.add(matrixWithTime);
                    }
                    break;
                }
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
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件时发生错误，请检查文件格式");
        }
        return returnResult;
    }

    @Override
    public <T> List<MatrixWithTime<T>> getDataByColumn(List<MatrixWithTime<T>> matrixWithTimes, String target,int position) {
        List<MatrixWithTime<T>> result = new ArrayList<>();
        for (MatrixWithTime<T> i : matrixWithTimes){
            List<List<T>> data = i.getData();
            for (List<T> j : data){
                try {
                    if (j.get(position).equals(target)){
                        MatrixWithTime<T> matrixWithTime = new MatrixWithTime<>();
                        matrixWithTime.setTime(i.getTime());
                        matrixWithTime.setData(Collections.singletonList(j));
                        result.add(matrixWithTime);
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
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
        String fistColumn;
        try {
            fistColumn = line.substring(0,position);
        } catch (StringIndexOutOfBoundsException e){
            e.printStackTrace();
            throw new RuntimeException("第一列没有这么多数据,当前读到的这一行数据为:{" + line + "}");
        }
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
