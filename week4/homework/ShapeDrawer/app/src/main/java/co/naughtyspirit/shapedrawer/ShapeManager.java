package co.naughtyspirit.shapedrawer;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import co.naughtyspirit.shapedrawer.shapes.MyShape;
import co.naughtyspirit.shapedrawer.shapes.Oval;
import co.naughtyspirit.shapedrawer.shapes.Shape;
import co.naughtyspirit.shapedrawer.shapes.Triangle;

public class ShapeManager {
    List<Shape> shapes;

    public void initialize() {
        shapes = new ArrayList<Shape>();
    }

    public void addShape(Context context, String shapeName, int position) {
        Shape shape;
        if(shapeName.equals("Triangle")) {
            shape = new Triangle(context, "Triangle");
        } else if(shapeName.equals("Oval")){
            shape = new Oval(context, "Oval");
        } else {
            shape = new MyShape(context, "MyShape2");
        }

        if(position == -1) {
            shapes.add(shape);
        } else {
            shapes.add(position, shape);
        }
    }

    public void removeShape(int index) {
        for(int i=0;i< shapes.size();i++) {
            if(i == index) {
                shapes.remove(i);
                break;
            }
        }
    }

    public void markAsSelected(int index) {
        for (int i = 0; i < shapes.size(); i++) {
            if(i == index) {
                shapes.get(i).setIsSelected(true);
            } else {
                shapes.get(i).setIsSelected(false);
            }
        }
    }

    public void resetSelectedShapes() {
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).setIsSelected(false);
        }
    }

    public List<Shape> getShapes() {
        return shapes;
    }
}
