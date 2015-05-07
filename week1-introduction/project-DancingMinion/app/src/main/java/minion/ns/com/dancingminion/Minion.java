package minion.ns.com.dancingminion;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/7/15.
 */
public class Minion {
    public static float getDegrees() {
        return 180f;
    }

    public static float getXPosition(float x) {
        return 100;
    }

    public static float getYPosition(float y) {
        return -200;
    }

    public static float getXScale(float scale) {
        return 1.2f;
    }

    public static float getYScale(float scale) {
        return 1.2f;
    }

    public static String getText(String text) {
        String result = text + " rules!";
        return result;
    }

    public static int getDuration() {
        return 1500;
    }

}
