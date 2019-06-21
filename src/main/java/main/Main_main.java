package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Solomon
 * @since 2019/6/20
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
public class Main_main {
    public static List<Double> result = new ArrayList<>();
    public static void main(String[] args) {
        Main.main(new String[]{"4"});
        List<Double> a = result;
        result = new ArrayList<>();
        Main.main(new String[]{"5"});
        List<Double> b = result;
        result = new ArrayList<>();
        Main.main(new String[]{"6"});
        List<Double> c = result;
        result = new ArrayList<>();
        Main.main(new String[]{"7"});
        List<Double> d = result;
        result = new ArrayList<>();
        Main.main(new String[]{"8"});
        List<Double> e = result;
        result = new ArrayList<>();
        Main.main(new String[]{"9"});
        List<Double> f = result;
        result = new ArrayList<>();
        Main.main(new String[]{"10"});
        List<Double> g = result;
        result = new ArrayList<>();
        for (int i = 0; i < 96; i++) {
            List<Double> tmp = new ArrayList<>();
            tmp.add(a.get(i));
            tmp.add(b.get(i));
            tmp.add(c.get(i));
            tmp.add(d.get(i));
            tmp.add(e.get(i));
            tmp.add(f.get(i));
            tmp.add(g.get(i));
            Double tt = Collections.min(tmp);
            result.add(tt);
        }
        System.out.println("==========================================================================");
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
