package co.naughtyspirit.wackyracer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import co.naughtyspirit.wackyracer.ui.ImmersiveActivity;


public class GameActivity extends ImmersiveActivity implements View.OnClickListener, CarCrashListener, GameTimerListener {

    private Board board;
    private CarEntity car;
    private GameTimer gameTimer;
    private TextView scoreboard;
    private TextView speedGauge;
    private TextView player;
    private TrafficAllocator trafficAllocator = new TrafficAllocator(this);
    private WackyRacer wackyRacer = new WackyRacer();
    private int speedIncrease;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViewById(R.id.left_btn).setOnClickListener(this);
        findViewById(R.id.right_btn).setOnClickListener(this);
        scoreboard = (TextView) findViewById(R.id.scoreboard);
        speedGauge = (TextView) findViewById(R.id.speed_gauge);
        player = (TextView) findViewById(R.id.player);
        startNewGame();
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
        findViewById(R.id.gui_layout).setVisibility(View.VISIBLE);
        scoreboard.setText(R.string.initial_score);
        Point size = getWindowSize();
        int width = size.x;
        int height = size.y;
        board = new Board(width, height, new Board.Size(Constants.BOARD_ROWS, Constants.BOARD_COLUMNS), (CanvasView) findViewById(R.id.canvas_view));

        createCar();

        int topSpeed = Math.min(car.getTopSpeed(), Constants.MAXIMUM_CAR_SPEED);
        int startSpeed = topSpeed / 2;
        speedGauge.setText(startSpeed + " km/h");
        speedIncrease = (topSpeed - startSpeed) / Constants.LEVEL_COUNT;
        gameTimer = new GameTimer(topSpeed, startSpeed, speedIncrease, this);
        gameTimer.start();
    }

    private void createCar() {
        String playerName = getIntent().getStringExtra(Constants.PLAYER_NAME_EXTRA);
        wackyRacer.createCar(playerName);
        Car playerCar = wackyRacer.getCar();
        CarColor color = playerCar.getColor();

        int carResId;
        switch (color) {

            case Blue:
                carResId = R.drawable.blue_car;
                break;

            case Green:
                carResId = R.drawable.green_car;
                break;

            case Yellow:
                carResId = R.drawable.yellow_car;
                break;

            default:
                carResId = R.drawable.red_car;
        }
        player.setText(playerCar.getDriver().getName());
        int startPosition = Constants.ROAD_COLUMNS[random.nextInt(Constants.ROAD_COLUMNS.length)];
        int playerStartPosition = playerCar.getPosition();
        if (playerStartPosition >= 1 && playerStartPosition <= 2) {
            startPosition = Constants.ROAD_COLUMNS[playerStartPosition - 1];
        }
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
        int firstRoadColumn = Constants.ROAD_COLUMNS[0];
        switch (v.getId()) {
            case R.id.left_btn:
                car.changePosition(firstRoadColumn + wackyRacer.moveLeft() - 1);
                board.checkForCollisions(car);
                break;

            case R.id.right_btn:
                car.changePosition(firstRoadColumn + wackyRacer.moveRight() - 1);
                board.checkForCollisions(car);
                break;
        }
        board.redraw();
    }

    @Override
    public void onCarCrash() {
        findViewById(R.id.gui_layout).setVisibility(View.GONE);
        board.onGameOver();
        gameTimer.cancel();
        int points = Integer.valueOf(scoreboard.getText().toString());
        wackyRacer.setPoints(points);
        showDialogGameOverDialog(R.string.crash_dialog_title, String.format(getString(R.string.crash_dialog_message), points), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((WackyRacerApp) getApplication()).addCar(wackyRacer.getCar());
                Intent intent = new Intent(GameActivity.this, LeaderboardActivity.class);
                intent.putExtra(Constants.NUMBER_OF_PLAYERS_EXTRA, wackyRacer.getNumberOfPlayers());
                startActivity(intent);
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
