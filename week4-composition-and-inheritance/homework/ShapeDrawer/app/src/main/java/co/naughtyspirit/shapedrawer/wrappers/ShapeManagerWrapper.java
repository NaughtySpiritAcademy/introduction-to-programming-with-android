package co.naughtyspirit.shapedrawer.wrappers;

import co.naughtyspirit.shapedrawer.ShapeManager;

/**
 * Created by nmp on 15-5-27.
 */
public class ShapeManagerWrapper {
    private static ShapeManager shapeManager;
    public static ShapeManager getInstance() {
        if(shapeManager == null) {
            shapeManager = new ShapeManager();
        }

        return shapeManager;
    }
}
