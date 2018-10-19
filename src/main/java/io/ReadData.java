package io;

import java.io.FileInputStream;
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
     * @return 返回以时间为区分的数组
     */
    <T> List<MatrixWithTime<T>> readAsMatrixWithTime(FileInputStream inputStream, String sign,Class<T> clazz);

    /**
     * 同上，保护第一列
     * @param inputStream 那个文件的输入流
     * @param sign 第一列最大占几位
     * @param clazz 利用反射去寻找String类型的构造函数,因为数据分析常用的
     *              Integer,Long,Double,Float都有String的构造参数，所以如果想要自定义类
     *              需要提供一个带有String的构造方法
     * @return 返回以时间为区分的数组
     */
    <T> List<MatrixWithTime<T>> readAsMatrixWithTimeProtectFirstColumn(FileInputStream inputStream, String sign,Class<T> clazz);





}
