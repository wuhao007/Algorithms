/*************************************************************************
 * Name: Mariano Simone
 * Email: mljsimone@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {
    
    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();
    
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate
    
    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point point1, Point point2) {
            // Calculate the slope of the point <this> points to with the <point1>
            double slope1 = slopeTo(point1);
            
            // Calculate the slope of the point <this> point to with the <point2>
            double slope2 = slopeTo(point2);
            
            return Double.compare(slope1, slope2);
        }
    }
    
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        
        // Degenerate line segments
        if (that.x == x && that.y == y)
            return Double.NEGATIVE_INFINITY;
        
        double dx = that.x - x,
               dy = that.y - y;
               
        // Horizontal line segments    
        if (dy == 0.0)
            return +0.0;
        
        // Vertical line segments
        if (dx == 0.0)
            return Double.POSITIVE_INFINITY;
        
        return dy / dx;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        
        // Avoid excessive property lookups
        int x0 = x, x1 = that.x,
            y0 = y, y1 = that.y;
            
        // this is less than that
        if (y0 < y1 || (y0 == y1 && x0 < x1))
            return -1;
        
        // this is equal to that
        if (x0 == x1 && y0 == y1)
            return 0;
        
        // this is greater than that
        return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    // Unit test
    public static void main(String[] args) {
        Point point1, point2;
        
        /* Vertical line segments should be +Infinity */
        point1 = new Point(5, 10);
        point2 = new Point(5, 7);
        assert point1.slopeTo(point2) == Double.POSITIVE_INFINITY
            : "Vertical line segments should be +Infinity";
            
        /* Horizontal line segments should be +0.0 */
        point1 = new Point(12, 3);
        point2 = new Point(3, 3);
        assert point1.slopeTo(point2) == +0.0
            : "Horizontal line segments should be +0.0";
            
        /* The slope of a point with himself should be -Infinity */
        Point p = new Point(1, 5);
        assert p.slopeTo(p) == Double.NEGATIVE_INFINITY
            : "The slpe of a point with himself should be -Infinity";
    }
}
