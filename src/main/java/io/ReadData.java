package io;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
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
public interface ReadData {

    /**
     * 同名重载，为了传入的时候直接能传path
     * @param path 文件路径
     * @param clazz 见另一个重载方法
     * @return 见另一个重载方法
     */
    <T> List<List<T>> readAsMatrix(String path,Class<T> clazz);


    /**
     * 同名重载，为了传入的时候直接能传path
     * @param path 文件路径
     * @param position 见另一个重载方法
     * @param clazz 见另一个重载方法
     * @return
     */
    <T> List<List<T>> readAsMatrixWithProtectFirstColumn(String path,int position,Class<T> clazz);

    /**
     * 同名重载，为了传入的时候直接能传path
     * @param path 文件路径
     * @param sign 见另一个重载方法
     * @param format 见另一个重载方法
     * @param clazz 见另一个重载方法
     * @return 见另一个重载方法
     */
    <T> List<MatrixWithTime<T>> readAsMatrixWithTime(String path, String sign, SimpleDateFormat format, Class<T> clazz);

    /**
     * 同名重载，为了传入的时候直接能传path
     * @param path 文件路径
     * @param format 见另一个重载方法
     * @param sign 见另一个重载方法
     * @param position 见另一个重载方法
     * @param clazz 见另一个重载方法
     * @return 见另一个重载方法
     */
    <T> List<MatrixWithTime<T>> readAsMatrixWithTimeProtectFirstColumn(String path, SimpleDateFormat format, String sign, int position,Class<T> clazz);
    /**
     * 原本数据是一个二维数组/矩阵
     * @param inputStream 要从那个文件读入的输入流
     * @param clazz 利用反射去寻找String类型的构造函数,因为数据分析常用的
     *              Integer,Long,Double,Float都有String的构造参数，所以如果想要自定义类
     *              需要提供一个带有String的构造方法
     * @return 返回一个二维数组，矩阵
     */
    <T> List<List<T>> readAsMatrix(FileInputStream inputStream,Class<T> clazz);

    /**
     * 原本数据是一个二维数组/矩阵，保护第一列（利用第一列最大占几位来做）
     * @param inputStream 那个文件的输入流
     * @param position 第一列最大占几位
     * @param clazz 利用反射去寻找String类型的构造函数,因为数据分析常用的
     *              Integer,Long,Double,Float都有String的构造参数，所以如果想要自定义类
     *              需要提供一个带有String的构造方法
     * @return 返回一个二维数组，矩阵
     */
    <T> List<List<T>> readAsMatrixWithProtectFirstColumn(FileInputStream inputStream,int position,Class<T> clazz);

    /**
     * 原本数据是一个二维数组/矩阵，但是一部分数据有一部分数据所对应的时间
     * 参考GPS收到的数据
     * @param inputStream 文件输入流
     * @param sign 时间出现那行对应的标志
     * @param clazz 利用反射去寻找String类型的构造函数,因为数据分析常用的
     *              Integer,Long,Double,Float都有String的构造参数，所以如果想要自定义类
     *              需要提供一个带有String的构造方法
     * @param format 时间那一行所对应的format
     * @return 返回以时间为区分的数组
     */
    <T> List<MatrixWithTime<T>> readAsMatrixWithTime(FileInputStream inputStream, String sign, SimpleDateFormat format, Class<T> clazz);

    /**
     * 同上，保护第一列
     * @param inputStream 那个文件的输入流
     * @param sign 第一列最大占几位
     * @param clazz 利用反射去寻找String类型的构造函数,因为数据分析常用的
     *              Integer,Long,Double,Float都有String的构造参数，所以如果想要自定义类
     *              需要提供一个带有String的构造方法
     * @param format 时间那一行所对应的format
     * @param position 第一列最大占几位
     * @return 返回以时间为区分的数组
     */
    <T> List<MatrixWithTime<T>> readAsMatrixWithTimeProtectFirstColumn(FileInputStream inputStream, SimpleDateFormat format, String sign, int position,Class<T> clazz);


    /**
     * 根据MatrixWithTime的data中的第一列来获取想要的数据
     * 用于从文件读取出来的数据二次加工
     * @param matrixWithTimes 需要二次提取的数据
     * @param position 需要提取的那一列,从0开始计数
     * @param target 目标数据的第一列的标志
     * @return 返回提取出来的结果
     */
    <T> List<MatrixWithTime<T>> getDataByColumn(List<MatrixWithTime<T>> matrixWithTimes, String target,int position);



}
