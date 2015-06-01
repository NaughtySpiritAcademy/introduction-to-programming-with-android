package co.naughtyspirit.wackyracer.entities;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/21/15.
 */
public class Position {
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public final int row;
    public final int column;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (row != position.row) return false;
        return column == position.column;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
