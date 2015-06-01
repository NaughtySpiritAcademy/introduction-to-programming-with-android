package co.naughtyspirit.wackyracer.utils;

import java.util.HashMap;
import java.util.Map;

import co.naughtyspirit.wackyracer.R;
import co.naughtyspirit.wackyracer.VehicleType;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/10/15.
 */
public interface Constants {
    int BOARD_ROWS = 10;
    int BOARD_COLUMNS = 6;
    int TRAFFIC_CAR_TYPE_COUNT = 8;

    int LEVEL_COUNT = 10;

    Integer[] ROAD_COLUMNS = {2, 3, 4, 5};
    int TRAFFIC_CAR_START_ROW = 1;
    int MAXIMUM_CAR_SPEED = 400;
    String BACKGROUND_COLOR = "#4CAF50";
    String PLAYER_NAME_EXTRA = "player_name";
    String VEHICLE_TYPE_EXTRA = "vehicle_type";
    String NUMBER_OF_PLAYERS_EXTRA = "number_of_players";
    int MOVES_PER_LEVEL = 30;

    Map<VehicleType, Integer> vehicleTypeToResource = new HashMap<VehicleType, Integer>() {{
        put(VehicleType.CAR, R.drawable.blue_car);
        put(VehicleType.MOTORCYCLE, R.drawable.red_motorcycle);
        put(VehicleType.SPACESHIP, R.drawable.red_spaceship);
    }};

}
