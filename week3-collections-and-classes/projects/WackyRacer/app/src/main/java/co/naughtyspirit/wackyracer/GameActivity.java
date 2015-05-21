package co.naughtyspirit.wackyracer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Random;

import co.naughtyspirit.wackyracer.entities.Board;
import co.naughtyspirit.wackyracer.entities.CarCrashListener;
import co.naughtyspirit.wackyracer.entities.CarEntity;
import co.naughtyspirit.wackyracer.ui.CanvasView;


public class GameActivity extends Activity implements View.OnClickListener, CarCrashListener, GameTimerListener {

    private Board board;
    private CarEntity car;
    private GameTimer gameTimer;
    private TextView scoreboard;
    private TextView speedGauge;
    private TrafficAllocator trafficAllocator = new TrafficAllocator(this);
    private WackyRacer wackyRacer = new WackyRacer();
    private int speedIncrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.left_btn).setOnClickListener(this);
        findViewById(R.id.right_btn).setOnClickListener(this);
        scoreboard = (TextView) findViewById(R.id.scoreboard);
        speedGauge = (TextView) findViewById(R.id.speed_gauge);
        startNewGame();
    }

    @Override
    protected void onResume() {
        enableImmersiveMode();
        super.onResume();
    }

    private void enableImmersiveMode() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

    private Point getWindowSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealSize(size);
        } else {
            display.getSize(size);
        }
        return size;
    }

    private void startNewGame() {
        scoreboard.setText(R.string.initial_score);

        Point size = getWindowSize();
        int width = size.x;
        int height = size.y;
        board = new Board(width, height, new Board.Size(Constants.BOARD_ROWS, Constants.BOARD_COLUMNS), (CanvasView) findViewById(R.id.canvas_view));
        Random random = new Random();
        createCar(random);

        int topSpeed = Math.min(car.getTopSpeed(), Constants.MAXIMUM_CAR_SPEED);
        int startSpeed = topSpeed / 2;
        speedGauge.setText(startSpeed + " km/h");
        speedIncrease = (topSpeed - startSpeed) / Constants.LEVEL_COUNT;
        gameTimer = new GameTimer(topSpeed, startSpeed, speedIncrease, this);
        gameTimer.start();
    }

    private void createCar(Random random) {
        Car playerCar = wackyRacer.createCar();
        String color = playerCar.getColor();

        int carResId;
        switch (color) {

            case "blue":
                carResId = R.drawable.blue_car;
                break;

            case "green":
                carResId = R.drawable.green_car;
                break;

            case "yellow":
                carResId = R.drawable.yellow_car;
                break;

            default:
                carResId = R.drawable.red_car;
        }
        int startPosition = Constants.ROAD_COLUMNS[random.nextInt(Constants.ROAD_COLUMNS.length)];
        car = new CarEntity(getResources().getDrawable(carResId), startPosition, playerCar.getTopSpeed(), this);
        board.add(car);
    }

    private void onMoveTiles() {
        updateScoreboard();
        board.moveTiles();
        trafficAllocator.allocate(board.getGameEntities(), board);
        board.checkForCollisions(car);
        board.redraw();
    }

    private void updateScoreboard() {
        int score = Integer.valueOf(scoreboard.getText().toString());
        score += 10;
        scoreboard.setText(score + "");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                car.moveLeft();
                board.checkForCollisions(car);
                break;

            case R.id.right_btn:
                car.moveRight();
                board.checkForCollisions(car);
                break;
        }
        board.redraw();
    }

    @Override
    public void onCarCrash() {
        board.onGameOver();
        gameTimer.cancel();
        showDialogGameOverDialog(R.string.crash_dialog_title, String.format(getString(R.string.crash_dialog_message), scoreboard.getText().toString()), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startNewGame();
            }
        });
    }

    @Override
    public void onMove() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onMoveTiles();
            }
        });
    }

    @Override
    public void onChangeSpeed(long currentSpeed) {
        String text = speedGauge.getText().toString();
        int speed = Integer.valueOf(text.replace("km/h", "").trim());
        speed += speedIncrease;
        speedGauge.setText(speed + " km/h");
    }

    private void showDialogGameOverDialog(int title, String message, DialogInterface.OnClickListener okListener) {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton("Ok", okListener).setCancelable(false).create();
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.show();
        dialog.getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility());
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }
}
