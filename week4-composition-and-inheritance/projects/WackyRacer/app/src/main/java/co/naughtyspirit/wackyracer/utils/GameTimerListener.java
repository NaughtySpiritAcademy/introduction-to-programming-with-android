package co.naughtyspirit.wackyracer.utils;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/21/15.
 */
public interface GameTimerListener {
    void onMove();

    void onChangeSpeed(long currentSpeed);
}
