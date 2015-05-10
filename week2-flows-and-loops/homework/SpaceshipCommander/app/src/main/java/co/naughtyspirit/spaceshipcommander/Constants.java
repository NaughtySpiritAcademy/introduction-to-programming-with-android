package co.naughtyspirit.spaceshipcommander;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/10/15.
 */
public interface Constants {
    int SHIP_MOVE_TIME = 1000;
    int CELL_STROKE_WIDTH_DENOMINATOR = 60;

    Map<Integer, String> BUTTON_IDS_TO_COMMAND = new HashMap<Integer, String>() {{
        put(R.id.up_btn, Command.Types.Up.name());
        put(R.id.down_btn, Command.Types.Down.name());
        put(R.id.left_btn, Command.Types.Left.name());
        put(R.id.right_btn, Command.Types.Right.name());
    }};

    int[] GUI_BUTTON_IDS = {R.id.left_btn, R.id.right_btn, R.id.up_btn, R.id.down_btn, R.id.start_btn, R.id.reset_btn};

    int BOARD_MIN_ROWS = 3;
    int BOARD_MIN_COLUMNS = 4;
    int BOARD_MAX_ROWS = 8;
    int BOARD_MAX_COLUMNS = 10;
    int EXTRA_BLACK_HOLES = 3;
}
