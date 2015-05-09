package co.naughtyspirit.spaceshipcommander;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Command {
    public int rows;
    public int columns;

    public Command(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public enum Types {
        Up, Down, Left, Right;
    }
}
