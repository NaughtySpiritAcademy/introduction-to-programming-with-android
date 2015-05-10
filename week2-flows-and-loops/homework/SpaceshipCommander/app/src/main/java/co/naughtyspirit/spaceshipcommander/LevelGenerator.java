package co.naughtyspirit.spaceshipcommander;

import java.util.Random;

import co.naughtyspirit.spaceshipcommander.entities.Board;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/10/15.
 */
public class LevelGenerator {

    private static int BOARD_MIN_ROWS = 3;
    private static int BOARD_MIN_COLUMNS = 4;

    private static int BOARD_MAX_ROWS = 8;
    private static int BOARD_MAX_COLUMNS = 10;

    private final Random random;

    private final Board.Size boardSize;

    private boolean[][] emptyPlaces = new boolean[BOARD_MAX_ROWS + 1][BOARD_MAX_COLUMNS + 1];

    public LevelGenerator() {
        random = new Random();
        boardSize = chooseBoardSize();
        initEmptyPlaces();
    }

    private void initEmptyPlaces() {
        for (int r = 1; r <= boardSize.rows; r++) {
            for (int c = 1; c <= boardSize.columns; c++) {
                emptyPlaces[r][c] = true;
            }
        }
    }

    private Board.Size chooseBoardSize() {
        int maxRowsToAdd = BOARD_MAX_ROWS - BOARD_MIN_ROWS;
        int maxColumnsToAdd = BOARD_MAX_COLUMNS - BOARD_MIN_COLUMNS;
        return new Board.Size(BOARD_MIN_ROWS + random.nextInt(maxRowsToAdd) + 1, BOARD_MIN_COLUMNS + random.nextInt(maxColumnsToAdd) + 1);
    }

    public Board.Position chooseRandomBoardPosition() {
        int row;
        int column;
        do {
            row = random.nextInt(boardSize.rows) + 1;
            column = random.nextInt(boardSize.columns) + 1;
        } while (!emptyPlaces[row][column]);
        emptyPlaces[row][column] = false;
        return new Board.Position(row, column);
    }

    public int choosePassengerCount() {
        return random.nextInt(50);
    }

    public Board.Size getBoardSize() {
        return boardSize;
    }


}
