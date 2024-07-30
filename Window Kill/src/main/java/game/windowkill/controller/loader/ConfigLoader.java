package game.windowkill.controller.loader;

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
}
