package co.naughtyspirit.wackyracer;

import android.os.Handler;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/21/15.
 */
public class GameTimer {

    private final GameTimerListener listener;
    private Handler moveTilesHandler = new Handler();
    private int tickCount = Constants.FIRST_LEVEL_TICK_COUNT;
    private int currentTicks = 0;
    private long moveInterval = Constants.FIRST_LEVEL_MOVE_INTERVAL;
    private boolean isRunning = false;

    private Runnable moveTilesRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isRunning) {
                return;
            }
            listener.onMove();

            currentTicks++;
            if (currentTicks >= tickCount) {
                currentTicks = 0;
                if (tickCount > 0) {
                    tickCount -= 2;
                    moveInterval -= 10;
                    listener.onChangeSpeed();
                }

            }

            moveTilesHandler.postDelayed(this, moveInterval);
        }
    };

    public GameTimer(GameTimerListener listener) {
        this.listener = listener;
    }


    public void start() {
        isRunning = true;
        moveTilesHandler.postDelayed(moveTilesRunnable, moveInterval);
    }

    public void cancel() {
        isRunning = false;
        moveTilesHandler.removeCallbacks(moveTilesRunnable);
    }
}
