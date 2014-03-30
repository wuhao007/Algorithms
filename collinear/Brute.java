/*************************************************************************
 * Name: Mariano Simone
 * Email: mljsimone@gmail.com
 *
 * Compilation:  javac Brute.java
 * Execution: java Brute
 * Dependencies: StdDraw.java Point.java
 *
 * Description: Find all collinear points in a given set
 *
 *************************************************************************/

public class Brute {
    
    public static void main(String[] args) {
        
        int n = 0;
        
        In inputFile;
        Point[] points;
        
        Point p, q, r, s;
        double slopepq, slopepr, slopeps;
        
        inputFile = new In(args[0]);
        
        // How many points do we have in the input file?
        n = inputFile.readInt();
        
        // Allocate enough space for them
        points = new Point[n];
        
        // Rescale coordinate system for proper visualization.
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        for (int i = 0; !inputFile.isEmpty(); i++) {
            int x = inputFile.readInt();
            int y = inputFile.readInt();
            
            points[i] = new Point(x, y);
        }
        
        for (int l0 = 0; l0 < n; l0++) {
            
            p = points[l0];
            p.draw();
            
            for (int l1 = 0; l1 < n; l1++) {
                
                if (l1 == l0)
                    continue;
                
                q = points[l1];    
                slopepq = p.slopeTo(q);
                
                // To print only the combination of points
                // that go in ascending order
                if (p.compareTo(q) > -1)
                    continue;
                
                for (int l2 = 0; l2 < n; l2++) {
                    
                    if (l2 == l1)
                        continue;
                    
                    r = points[l2];
                    
                    // To print only the combination of points
                    // that go in ascending order
                    if (q.compareTo(r) > -1)
                        continue;
                    
                    slopepr = p.slopeTo(r);
                    
                    // if p, q and r aren't collinear, skip the rest.
                    if (slopepq != slopepr)
                        continue;
                    
                    for (int l3 = 0; l3 < n; l3++) {                        
                        
                        if (l3 == l2)
                            continue;
                        
                        s = points[l3];
                        
                        // To print only the combination of points
                        // that go in ascending order
                        if (r.compareTo(s) > -1)
                            continue;
                        
                        slopeps = p.slopeTo(s);
                        
                        // To print only the combination of points
                        // that go in ascending order
                        if (slopepq != slopeps)
                            continue;
                        
                        p.drawTo(s);
                        StdOut.println(p + " -> " + q + " -> " + r + " -> " + s);
                    }
                }
            }
        }
    }
}