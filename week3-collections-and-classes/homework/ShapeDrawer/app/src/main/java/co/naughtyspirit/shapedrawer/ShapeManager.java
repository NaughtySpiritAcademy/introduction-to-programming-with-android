package co.naughtyspirit.shapedrawer;

import java.util.ArrayList;
import java.util.List;

import co.naughtyspirit.shapedrawer.shapes.Shape;

public class ShapeManager {
    static List<Shape> shapes = new ArrayList<Shape>();
    public static void addShape(Shape shape, int position) {
        if(position == -1) {
            shapes.add(shape);
        } else {
            shapes.add(0, shape);
        }
    }

    public static void removeShape(int index) {
        for(int i=0;i< shapes.size();i++) {
            if(i == index) {
                shapes.remove(i);
                break;
            }
        }
    }

    public static List<Shape> getShapes() {
        return shapes;
    }
}
