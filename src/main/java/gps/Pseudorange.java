package gps;

import io.PseudorangeModule;
import model.Point;
import model.PointE;
import model.PointWithErrorAndPesudorange;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
public interface Pseudorange {

    /**
     * 计算Rj 参数参考
     * <p>
     * R1=sqrt((x1-xu(i))^2+(y1-yu(i))^2+(z1-zu(i))^2);
     * R2=sqrt((x2-xu(i))^2+(y2-yu(i))^2+(z2-zu(i))^2);
     * R3=sqrt((x3-xu(i))^2+(y3-yu(i))^2+(z3-zu(i))^2);
     * R4=sqrt((x4-xu(i))^2+(y4-yu(i))^2+(z4-zu(i))^2);
     * R5=sqrt((x5-xu(i))^2+(y5-yu(i))^2+(z5-zu(i))^2);
     * R6=sqrt((x6-xu(i))^2+(y6-yu(i))^2+(z6-zu(i))^2);
     *
     * @param xi
     * @param xui
     * @param yi
     * @param yui
     * @param zi
     * @param zui
     * @return
     */
    double calcRj(double xi, double xui, double yi, double yui, double zi, double zui);

    /**
     * 计算Gu
     *
     * @param data Gu的组成矩阵
     * @return 返回Gu
     */
    double[][] calcGu(List<PointE> data);

    /**
     * 计算Ej
     * 参数参考
     * ex1=(x1-xu(i))/R1;
     * ey1=(y1-yu(i))/R1;
     * ez1=(z1-zu(i))/R1;
     * ex2=(x2-xu(i))/R2;
     * ey2=(y2-yu(i))/R2;
     * ez2=(z2-zu(i))/R2;
     * ex3=(x3-xu(i))/R3;
     * ey3=(y3-yu(i))/R3;
     * ez3=(z3-zu(i))/R3;
     * ex4=(x4-xu(i))/R4;
     * ey4=(y4-yu(i))/R4;
     * ez4=(z4-zu(i))/R4;
     * ex5=(x5-xu(i))/R5;
     * ey5=(y5-yu(i))/R5;
     * ez5=(z5-zu(i))/R5;
     * ex6=(x6-xu(i))/R6;
     * ey6=(y6-yu(i))/R6;
     * ez6=(z6-zu(i))/R6;
     *
     * @param ex    对应ex1
     * @param ey    对应ey1
     * @param ez    对应ez1
     * @param delta 偏差量
     * @return 返回Ej
     */
    Double[] calcEj(double ex, double ey, double ez, double delta);

    /**
     * 通过伪距来计算坐标，出入数据参考
     * xu(1)=0;
     * yu(1)=0;
     * zu(1)=0;
     * tu(1)=0;  %Éè¶¨½ÓÊÕ»ú×ø±êºÍ½ÓÊÕ»úÖÓ²î³õÖµÎª0
     * <p>
     * % x1=P(1,1);  %%PRN2
     * % y1=P(1,2);
     * % z1=P(1,3);
     * % x2=P(2,1);  %%PRN5
     * % y2=P(2,2);
     * % z2=P(2,3);
     * % x3=P(3,1);  %%PRN9
     * % y3=P(3,2);
     * % z3=P(3,3);
     * % x4=P(4,1);  %%PRN12
     * % y4=P(4,2);
     * % z4=P(4,3);
     * % x5=P(5,1);  %%PRN13
     * % y5=P(5,2);
     * % z5=P(5,3);
     * % x6=P(6,1);  %%PRN19
     * % y6=P(6,2);
     * % z6=P(6,3);
     * <p>
     * x1=21659.073921  ;  %%PRN2
     * y1= -14866.104583 ;
     * z1=-2694.605345;
     * x2=18783.395660      ;  %%PRN5
     * y2=-5766.721206;
     * z2= 17844.829683;
     * <p>
     * x3= 8637.308686       ;  %%PRN9
     * y3= 23535.702817 ;
     * z3= 8715.667725;
     * <p>
     * x4=1467.900758  ;  %%PRN12
     * y4= -15666.014698  ;
     * z4=-21640.813603;
     * <p>
     * x5=12650.349761      ;  %%PRN13
     * y5=-15238.113197;
     * z5=17565.530198 ;
     * <p>
     * x6=14816.392893     ;  %%PRN19
     * y6= 2297.687348;
     * z6=-22223.358928;
     * <p>
     * p1=198280326.67246;
     * p2=20704587.611;
     * p3=24100368.978;
     * p4=24036232.030;
     * p5=22995351.484;
     * p6=23238570.404;
     * % p1=20973542.329;
     * % p2=20704587.611;
     * % p3=24100368.978;
     * % p4=24036232.030;
     * % p5=22995351.484;
     * % p6=23238570.404;  %%ÊäÈë15ºÅÕ¾µã²âµÃµÄÎ±¾à
     * <p>
     * b1=-5.33848069608e-05;
     * b2=-3.24565917253e-07;
     * b3=4.94070816785e-04;
     * b4=2.93569173664e-04;
     * b5=-8.49892385304e-05;
     * b6=-3.85542400181e-04;  %%ÊäÈëÎÀÐÇÖÓ²î
     * <p>
     * c=299792458;
     * B1=b1*c;
     * B2=b2*c;
     * B3=b3*c;
     * B4=b4*c;
     * B5=b5*c;
     * B6=b6*c;
     *
     * @param data
     * @return
     */
    Point calcPointPositionByPseudorange(List<PointWithErrorAndPesudorange> data);


    /**
     * 从文件读取伪距内容
     * 参考文件chan1560.19o
     * @param path
     * @return 返回的key是时间，value是对应时间的卫星信息
     */
    Map<Date, PseudorangeModule> readDataFromFile(String path);


}
