public class PointSET {
    private final SET<Point2D> set = new SET<Point2D>();

    /*
     * construct an empty set of points
     */
    public PointSET() {
    }

    /*
     * is the set empty?
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /*
     * number of points in the set
     */
    public int size() {
        return set.size();
    }

    /*
     * add the point p to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        if (set.contains(p)) {
            return;
        }
        set.add(p);
    }

    /*
     * does the set contain the point p?
     */
    public boolean contains(Point2D p) {
        return set.contains(p);
    }

    /*
     * draw all of the points to standard draw
     */
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        for (Point2D p : set) {
            p.draw();
        }
    }

    /*
     * all points in the set that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> queue = new Queue<Point2D>();
        for (Point2D p : set) {
            if (rect.contains(p)) {
                queue.enqueue(p);
            }
        }
        return queue;
    }

    /*
     * a nearest neighbor in the set to p; null if set is empty
     */
    public Point2D nearest(Point2D p) {
        double distance = Double.MAX_VALUE;
        Point2D nearest = null;
        for (Point2D other : set) {
            if (p.distanceTo(other) < distance) {
                distance = p.distanceTo(other);
                nearest = other;
            }

        }
        return nearest;
    }
}