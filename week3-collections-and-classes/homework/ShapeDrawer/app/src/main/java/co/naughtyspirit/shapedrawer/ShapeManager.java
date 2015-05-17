package co.naughtyspirit.shapedrawer;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import co.naughtyspirit.shapedrawer.shapes.Oval;
import co.naughtyspirit.shapedrawer.shapes.Shape;
import co.naughtyspirit.shapedrawer.shapes.Triangle;

public class ShapeManager {
    static List<Shape> shapes = new ArrayList<Shape>();
    public static void addShape(Context context, String shapeName, int position) {
        Shape shape;
        if(shapeName.equals("Triangle")) {
            shape = new Triangle(context);
        } else {
            shape = new Oval(context);
        }

        if(position == -1) {
            shapes.add(shape);
        } else {
            shapes.add(position, shape);
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

    public static void markAsSelected(int index) {
        for (int i = 0; i < shapes.size(); i++) {
            if(i == index) {
                shapes.get(i).setIsSelected(true);
            } else {
                shapes.get(i).setIsSelected(false);
            }
        }
    }

    public static void resetSelected() {
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).setIsSelected(false);
        }
    }

    public static List<Shape> getShapes() {
        return shapes;
    }
}
