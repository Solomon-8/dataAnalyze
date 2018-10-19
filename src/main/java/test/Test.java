package test;

import io.MatrixWithTime;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
public class Test {
    public static void main(String[] args) throws IOException {
//        String path = "C:\\Users\\Solomon\\Documents\\Tencent Files\\914573285\\FileRecv\\email.txt";
//        FileInputStream fileInputStream = new FileInputStream(path);
//        FileChannel channel = fileInputStream.getChannel();
//        int a = 100;
//        ByteBuffer bf = ByteBuffer.allocate(a);
//        System.out.println("限制是：" + bf.limit() + "容量是：" + bf.capacity()
//                + "位置是：" + bf.position());
//        int length = -1;
//        while ((length = channel.read(bf)) != -1) {
//
//            /*
//             * 注意，读取后，将位置置为0，将limit置为容量, 以备下次读入到字节缓冲中，从0开始存储
//             */
//            bf.clear();
//            byte[] bytes = bf.array();
//            System.out.write(bytes, 0, length);
//            System.out.println();
//
//            System.out.println("限制是：" + bf.limit() + "容量是：" + bf.capacity()
//                    + "位置是：" + bf.position());

//        }
//        String test = "nice  to meet you;";
//        String[] s = test.trim().split(" ");
//        List<String> list = new ArrayList<String>();
//        for (String i : s){
//            if (StringUtils.isNotEmpty(i)){
//                list.add(i);
//            }
//        }
//        System.out.println(Arrays.toString(s));
//        System.out.println(list);
//        Integer integer = new Integer("1234567890");
//        Long l = new Long("123456789012354");
//        Double d = new Double("1231564.1546");
//        Float f = new Float("1234564897.564");
//        System.out.println(integer);
//        System.out.println(l);
//        System.out.println(d);
//        System.out.println(f);
//        String test = "solomon aa";
//        System.out.println(test.substring(0,2));
//        System.out.println(test.substring(2));
        List<List<String>> a = new ArrayList<>();
        List<String> list = new LinkedList<>();
        list.add("123");
        list.add("456");
        list.add("789");
        List<String> list1 = new LinkedList<>();
        list1.add("asd");
        a.add(list);
        temp temp = new temp();
        temp.a = a;
        System.out.println(temp);
        a = new ArrayList<>();
        a.add(list1);
        temp tm = new temp();
        tm.a = a;
        System.out.println(temp);
        System.out.println(tm);
    }

     static class temp{
        List<List<String>> a;

        @Override
        public String toString() {
            return "temp{" +
                    "a='" + a + '\'' +
                    '}';
        }
    }
}
