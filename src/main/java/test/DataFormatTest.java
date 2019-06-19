package test;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
public class DataFormatTest {
    public static void main(String[] args) throws ParseException, IOException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Solomon\\Desktop\\GPS\\chan1560.19o");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy MM dd HH mm ss");
        String line = reader.readLine();
        while (line != null) {
            int count = 0;
            if (line.startsWith(" 19")) {
                String[] data = line.split(" ");
                List<String> tmp = new ArrayList<>();
                for (String s : data) {
                    if (StringUtils.isNotBlank(s)) {
                        tmp.add(s);
                    }
                }
                int index = tmp.get(7).indexOf("G");
                count = Integer.parseInt(tmp.get(7).substring(0, index));
                String[] gpsData = tmp.get(7).split("G");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(tmp.get(0)).append(" ");
                stringBuilder.append(tmp.get(1)).append(" ");
                stringBuilder.append(tmp.get(2)).append(" ");
                stringBuilder.append(tmp.get(3)).append(" ");
                stringBuilder.append(tmp.get(4)).append(" ");
                stringBuilder.append(tmp.get(5)).append(" ");
                stringBuilder.append(tmp.get(6)).append(" ");
                Date date = simpleDateFormat.parse(stringBuilder.toString());
                System.out.println(date.getTime());
                System.out.println(date);
                Map<String, Double> a = new HashMap<>(48);

                for (int i = 0; i < count; i++) {
                    line = reader.readLine();
                    String[] pseudorange = line.split(" ");
                    List<String> pseudorangeData = new ArrayList<>();
                    for (String s : pseudorange) {
                        if (StringUtils.isNotBlank(s)) {
                            pseudorangeData.add(s);
                        }
                    }
                    System.out.println(pseudorangeData);
                    a.put("G" + gpsData[i + 1], Double.valueOf(pseudorangeData.get(2)));
                    reader.readLine();
                }
            }
            line = reader.readLine();
        }
    }

}
