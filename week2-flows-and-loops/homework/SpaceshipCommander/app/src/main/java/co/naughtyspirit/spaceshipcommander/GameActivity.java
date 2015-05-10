package co.naughtyspirit.spaceshipcommander;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;

import co.naughtyspirit.spaceshipcommander.entities.BlackHole;
import co.naughtyspirit.spaceshipcommander.entities.Board;
import co.naughtyspirit.spaceshipcommander.entities.Planet;
import co.naughtyspirit.spaceshipcommander.entities.Ship;
import co.naughtyspirit.spaceshipcommander.entities.ShipListener;
import co.naughtyspirit.spaceshipcommander.ui.CanvasView;


public class GameActivity extends Activity implements View.OnClickListener, ShipListener {

    private Board board;
    private Ship ship;
    private TextView commandList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableImmersiveMode();
        bindViews();
        startNewGame();
    }

    private void startNewGame() {
        LevelGenerator levelGenerator = new LevelGenerator();
        Board.Size boardSize = levelGenerator.getBoardSize();
        Drawable background = getResources().getDrawable(R.drawable.background);
        Point size = getWindowSize();
        int width = size.x;
        int height = size.y;
        board = new Board(width, height, boardSize, background, (CanvasView) findViewById(R.id.canvas_view));
        int shipPassengerCount = Commander.getShip(levelGenerator.choosePassengerCount());
        ship = new Ship(levelGenerator.chooseRandomBoardPosition(), getResources().getDrawable(shipPassengerCount), this);
        board.add(ship);
        for (int i = 0; i < boardSize.rows; i++) {
            board.add(new BlackHole(levelGenerator.chooseRandomBoardPosition(), getResources().getDrawable(R.drawable.black_hole)));
        }
        board.add(new Planet(levelGenerator.chooseRandomBoardPosition(), getResources().getDrawable(R.drawable.planet)));
    }

    private void bindViews() {
        for (int buttonId : Constants.GUI_BUTTON_IDS) {
            findViewById(buttonId).setOnClickListener(this);
        }
        commandList = (TextView) findViewById(R.id.command_list);
    }

    private void onStartGame() {
        for (int buttonId : Constants.GUI_BUTTON_IDS) {
            findViewById(buttonId).setVisibility(View.GONE);
        }
        ship.executeCommands(prepareCommands());
    }

    private Queue<Command> prepareCommands() {
        Queue<Command> commandQueue = new LinkedList<>();
        int[][] commands = Commander.getCommands(commandList.getText().toString().split(", "));
        for (int[] command : commands) {
            commandQueue.add(new Command(command[0], command[1]));
        }
        return commandQueue;
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_btn) {
            onStartGame();
        } else if (v.getId() == R.id.reset_btn) {
            onResetCommandList();
        } else {
            if (commandList.getText().length() != 0) {
                commandList.append(", ");
            }
            commandList.append(Constants.BUTTON_IDS_TO_COMMAND.get(v.getId()));
        }
    }

    private void onResetCommandList() {
        onGameOver();
    }

    private void onGameOver() {
        commandList.setText("");
        for (int buttonId : Constants.GUI_BUTTON_IDS) {
            findViewById(buttonId).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCollisionWithObstacle() {
        onGameOver();
        showDialogGameOverDialog(R.string.mission_failed, R.string.into_black_hole, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetLevel();
            }
        });
    }

    @Override
    public void onLandingOnPlanet() {
        onGameOver();
        showDialogGameOverDialog(R.string.mission_success, R.string.you_are_awesome, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startNewGame();
            }
        });
    }

    @Override
    public void onCollisionWithBoard() {
        onGameOver();
        showDialogGameOverDialog(R.string.mission_failed, R.string.epic_fail, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetLevel();
            }
        });
    }

    @Override
    public void onPlanetNotReachedAfterExecutingCommands() {
        onGameOver();
        showDialogGameOverDialog(R.string.mission_failed, R.string.try_again, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetLevel();
            }
        });
    }

    @Override
    public void onCommandExecuted() {
        board.onCommandExecuted(ship);
    }

    private void resetLevel() {
        board.resetLevel(ship);
    }

    private void showDialogGameOverDialog(int title, int message, DialogInterface.OnClickListener okListener) {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton("Ok", okListener).setCancelable(false).create();
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.show();
        dialog.getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility());
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }
}
