package com.example.ns.dancingminion;

public class Minion {
    public static float rotate() {
        float circle = 360;
        float quarterCircle = circle * 3;
        return quarterCircle;
    }

    public static float goToRightBananas(float x) {
        float displayWidth = 800;
        float result = displayWidth - x;
        return result;
    }

    public static float goToBottomRightBanana(float y) {
        return -200;
    }

    public static float goToBottomLeftBanana(float x) {
        return -300;
    }

    public static float zoomMinionWidth(float scale) {
        return 1.2f;
    }

    public static float zoomMinionHeight(float scale) {
        return 0.8f;
    }

    public static String minionAwesomeText(String text) {
        String result = text + " rules!";
        return result;
    }

    public static int getDuration() {
        return 1500;
    }

}
