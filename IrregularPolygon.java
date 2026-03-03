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

    // Calculate perimeter by adding together the sum of the distance between one
    // point and the next
    public double perimeter() {
        double sum = 0;
        for (int i = 0; i < myPolygon.size() - 1; i++) {
            sum += myPolygon.get(i).distance(myPolygon.get(i + 1));
        }
        sum += myPolygon.get(myPolygon.size() - 1).distance(myPolygon.get(0));
        return sum;
    }

    // Calculate area using shoelace formula
    // In this case, I condense (+ X0*Y1 - Y0*X1) into one line because they use the same
    // i and i+1 values - no need to run through the ArrayList again
    public double area() {
        double sum = 0;
        for (int i = 0; i < myPolygon.size() - 1; i++) {
            sum += sum(myPolygon, i, i + 1);
        }
        sum += sum(myPolygon, myPolygon.size() - 1, 0);
        sum /= 2;
        return Math.abs(sum);
    }

    // Use method for repeated code to condense
    public double sum(ArrayList<Point2D.Double> polygon, int a, int b) {
        return (polygon.get(a).getX() * polygon.get(b).getY()) - (polygon.get(a).getY() * polygon.get(b).getX());
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
            pen.up();

        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}
