package game.windowkill.controller.loader;

import game.windowkill.controller.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConfigLoader {
    private static ConfigLoader configLoader;

    public static ConfigLoader getInstance() {
        if(configLoader == null)
            configLoader = new ConfigLoader();
        return configLoader;
    }

    public ConfigLoader(){
        //todo : config
    }

    private int a1 = 750, a2 = 1000, a3 = 1500;
    private int d1 = 500, d2 = 750, d3 = 900, d4 = 1200;
    private int c1 = 1000, c2 = 750, c3 = 1500;

    public static final String path = Constant.SAVE;

    public int getA1() {
        return a1;
    }

    public int getA2() {
        return a2;
    }

    public int getA3() {
        return a3;
    }

    public int getD1() {
        return d1;
    }

    public int getD2() {
        return d2;
    }

    public int getD3() {
        return d3;
    }

    public int getD4() {
        return d4;
    }

    public int getC1() {
        return c1;
    }

    public int getC2() {
        return c2;
    }

    public int getC3() {
        return c3;
    }

    public void saveRound(int xp, int time) {
        try {
            PrintWriter writer = new PrintWriter(new File(path).toURI().toString());
            writer.println(xp + " " + time);
            writer.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int getXp() {
        int ans = 0;
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNext()){
            int xp = Integer.parseInt(sc.next());
            sc.next();
            ans += xp;
//            System.out.println(sc.next() + " i");
        }
        return ans;
    }
}
