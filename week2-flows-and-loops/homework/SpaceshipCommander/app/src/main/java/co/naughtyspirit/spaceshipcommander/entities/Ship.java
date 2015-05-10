package co.naughtyspirit.spaceshipcommander.entities;

import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;

import java.util.List;
import java.util.Queue;

import co.naughtyspirit.spaceshipcommander.Command;
import co.naughtyspirit.spaceshipcommander.Constants;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Ship extends GameEntity {

    private final ShipListener shipListener;
    private final Board.Position initialPosition;
    private Queue<Command> commands;
    private boolean hasCollided;

    public Ship(Board.Position position, Drawable image, ShipListener shipListener) {
        super(position, image);
        this.initialPosition = position;
        this.shipListener = shipListener;
        hasCollided = false;
    }


    public void resetPosition() {
        hasCollided = false;
        position = initialPosition;
    }


    public void executeCommand(Command command) {
        position = new Board.Position(position.row + command.rows, position.column + command.columns);
    }

    public void executeCommands(Queue<Command> commands) {
        this.commands = commands;
        executeNextCommand();
    }

    public void checkForEntityCollisions(List<GameEntity> gameEntities) {
        for (GameEntity entity : gameEntities) {
            if (entity.position.row == position.row && entity.position.column == position.column) {
                if (entity instanceof BlackHole) {
                    hasCollided = true;
                    shipListener.onFallingInBlackHole();
                } else if (entity instanceof Planet && commands.isEmpty()) {
                    hasCollided = true;
                    shipListener.onLandingOnPlanet();
                }
            }
        }
    }

    public void checkForBoardBounds(Board.Size boardSize) {
        if (position.row <= 0 || position.row > boardSize.rows || position.column <= 0 || position.column > boardSize.columns) {
            hasCollided = true;
            shipListener.onOutsideOfGalaxy();
        }
    }

    private void executeNextCommand() {
        new CountDownTimer(Constants.SHIP_MOVE_TIME, Constants.SHIP_MOVE_TIME) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (commands.isEmpty()) {
                    shipListener.onPlanetNotReachedAfterExecutingCommands();
                    return;
                }
                Command command = commands.poll();
                executeCommand(command);
                shipListener.onCommandExecuted();
                if (!hasCollided) {
                    executeNextCommand();
                }
            }
        }.start();
    }
}
