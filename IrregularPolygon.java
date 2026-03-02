import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import java.util.concurrent.TimeUnit;

import gpdraw.*; // for DrawingTool

public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    // constructor
    public IrregularPolygon() {
    }

    // public methods
    public void add(Point2D.Double aPoint) {
        myPolygon.add(aPoint);
    }

    public double perimeter() {
        double sum = 0;
        for (int i = 0; i < myPolygon.size()-1; i++) {
            sum += myPolygon.get(i).distance(myPolygon.get(i+1));
        }
        sum += myPolygon.get(myPolygon.size()-1).distance(myPolygon.get(0));
        return sum;
    }

    public double area() {
        double sum = 0;
        for (int i = 0; i < myPolygon.size()-1; i++) {
            sum += myPolygon.get(i).getX() * myPolygon.get(i+1).getY();
            sum -= myPolygon.get(i).getY() * myPolygon.get(i+1).getX();
        }
        sum += myPolygon.get(myPolygon.size()-1).getX() * myPolygon.get(0).getY();
        sum -= myPolygon.get(myPolygon.size()-1).getY() * myPolygon.get(0).getX();
        sum /= 2;
        return Math.abs(sum);
    }

    public void draw() {
        // Wrap the DrawingTool in a try/catch to allow development without need for
        // graphics.
        try {
            // TODO: Draw the polygon.
            // Documents: https://pavao.org/compsci/gpdraw/html/gpdraw/DrawingTool.html
            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            pen.up();
            
            for (Point2D.Double point : myPolygon) {
                pen.move(point.getX(), point.getY());
                pen.down();
            }
            pen.move(myPolygon.get(0).getX(), myPolygon.get(0).getY());
            
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}
