package co.naughtyspirit.wackyracer.utils;

import android.os.Handler;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/21/15.
 */
public class GameTimer {

    private final int topSpeed;
    private final int speedIncrease;
    private final GameTimerListener listener;
    private Handler moveTilesHandler = new Handler();
    private boolean isRunning = false;
    private long currentSpeed = 0;
    private int levelMoves = 0;

    private Runnable moveTilesRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isRunning) {
                return;
            }
            listener.onMove();

            if (currentSpeed < topSpeed && currentSpeed < Constants.MAXIMUM_CAR_SPEED) {
                levelMoves++;
                if (levelMoves > Constants.MOVES_PER_LEVEL) {
                    levelMoves = 0;
                    currentSpeed += speedIncrease;
                    if (currentSpeed + speedIncrease > Constants.MAXIMUM_CAR_SPEED) {
                        currentSpeed = Constants.MAXIMUM_CAR_SPEED;
                    }
                    listener.onChangeSpeed(currentSpeed);
                }
            }

            long delay = Constants.MAXIMUM_CAR_SPEED - currentSpeed;
            moveTilesHandler.postDelayed(this, delay);
        }
    };

    public GameTimer(int topSpeed, long currentSpeed, int speedIncrease, GameTimerListener listener) {
        this.topSpeed = topSpeed;
        this.currentSpeed = currentSpeed;
        this.speedIncrease = speedIncrease;
        this.listener = listener;
    }


    public void start() {
        isRunning = true;
        moveTilesHandler.postDelayed(moveTilesRunnable, Constants.MAXIMUM_CAR_SPEED);
    }

    public void cancel() {
        isRunning = false;
        moveTilesHandler.removeCallbacks(moveTilesRunnable);
    }
}
