package co.naughtyspirit.wackyracer;

import android.content.Context;

import java.util.List;
import java.util.Random;

import co.naughtyspirit.wackyracer.entities.Board;
import co.naughtyspirit.wackyracer.entities.GameEntity;
import co.naughtyspirit.wackyracer.entities.Position;
import co.naughtyspirit.wackyracer.entities.TrafficCarEntity;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/21/15.
 */
public class TrafficAllocator {


    private final Context context;
    private Random random = new Random();

    public TrafficAllocator(Context context) {
        this.context = context;
    }

    public void allocate(List<GameEntity> gameEntities, Board board) {
        for (GameEntity entity : gameEntities) {
            Position pos = entity.getPosition();
            if (pos.row == 1 || pos.row == 2) {
                return;
            }
        }

        if (shouldCreateTraffic()) {
            int trafficCarColumn = Constants.ROAD_COLUMNS[random.nextInt(Constants.ROAD_COLUMNS.length)];
            int trafficCarType = random.nextInt(Constants.TRAFFIC_CAR_TYPE_COUNT);
            String drawableName = "traffic_" + trafficCarType;
            int resId = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
            TrafficCarEntity trafficCar = new TrafficCarEntity(new Position(Constants.TRAFFIC_CAR_START_ROW, trafficCarColumn), context.getResources().getDrawable(resId));
            board.add(trafficCar);
        }
    }

    private boolean shouldCreateTraffic() {
        return random.nextInt(6) == 0;
    }
}
