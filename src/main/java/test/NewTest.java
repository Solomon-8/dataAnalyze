package test;

import io.Data;
import io.MatrixWithTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
public class NewTest {
    public static void main(String[] args) {
        Data data = new Data();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("> yyyy  M dd HH mm ss.0000000  0 SS");
//        SimpleDateFormat bSimpleDateFormat = new SimpleDateFormat(" > yy  M  dd  HH  mm  ss.0000000  0 SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        List<MatrixWithTime<String>> result = data.readAsMatrixWithTimeProtectFirstColumn("C:\\Users\\Solomon\\Desktop\\GPS\\802140156k.19O", simpleDateFormat, ">", 3, String.class);
//        List<MatrixWithTime<String>> bResult = data.readAsMatrixWithTime("C:\\Users\\Solomon\\Desktop\\GPS\\bjfs1560.19o", ">", bSimpleDateFormat, String.class);
//        System.out.println(bResult);
        List<MatrixWithTime<String>> aa = data.getDataByColumn(result, "G16", 0);
        List<MatrixWithTime<String>> bb = filterByTime(aa, 15);
        System.out.println(result);
        System.out.println(aa);
        System.out.println(bb);
    }

    public static List<MatrixWithTime<String>> filterByTime(List<MatrixWithTime<String>> data, int time) {
        List<MatrixWithTime<String>> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date first = data.get(0).getTime();
        calendar.setTime(first);
        calendar.add(Calendar.MINUTE, time);
        Date next = calendar.getTime();
        boolean firstFlag = true;
        for (MatrixWithTime<String> entity : data) {
            if (firstFlag) {
                result.add(entity);
                firstFlag = false;
                continue;
            }
            if (entity.getTime().getTime() / 1000 == next.getTime() / 1000 || entity.getTime().after(next)) {
                result.add(entity);
                calendar.setTime(entity.getTime());
                calendar.add(Calendar.MINUTE, time);
                next = calendar.getTime();
            }
        }
        return result;
    }

    public static List<MatrixWithTime<String>> filterByTimeAndName(List<MatrixWithTime<String>> data, String name, Date time) {
        List<MatrixWithTime<String>> result = new ArrayList<>();
        for (MatrixWithTime<String> matrix : data) {
//            if (matrix.getTime().equals(time)) {
//                List<List<String>> lists = matrix.getData();
//                for (List<String> list : lists) {
//                    if (list.get(0).equals(name)) {
//                        List<List<String>> tmp = new ArrayList<>();
//                        tmp.add(list);
//                        return new MatrixWithTime<>(time, tmp);
//                    }
//                }
//                throw new RuntimeException("小可爱， 当前时间段没有卫星:" + name + ",所有卫星数据为:" + lists + "\n" + "对应时间点为:" + time);
//            }
            List<List<String>> lists = matrix.getData();
            for (List<String> list : lists) {
                if (list.get(0).equals(name)) {
                    List<List<String>> tmp = new ArrayList<>();
                    tmp.add(list);
                    result.add(new MatrixWithTime<>(matrix.getTime(), tmp));
                }
            }
        }
        return result;
//        throw new RuntimeException("小可爱， 没有当前时间段:" + time);
    }

    public static List<List<MatrixWithTime<String>>> filterByTimeAndNames(List<MatrixWithTime<String>> data, Date time, String... name) {
        List<List<MatrixWithTime<String>>> list = new ArrayList<>();
        for (String s : name) {
            List<MatrixWithTime<String>> tmp = filterByTimeAndName(data, s, time);
            list.add(tmp);
        }
        return list;
    }
}
