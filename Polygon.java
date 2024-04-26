package org.example;

import java.util.List;

public class Polygon {
    private List<Point> points;

    public Polygon(List<Point> points) {
        this.points = points;
    }
    public boolean inside(Point point){
        int counter = 0;
        double x;
        int n = points.size();
        for(int i = 0; i < n; i++) {
            Point pa = points.get(i);
            Point pb = points.get((i + 1) % n);
            if (pa.y() > pb.y()) {
                Point temp = pa;
                pa = pb;
                pb = temp;
            }
            if (pa.y() < point.y() && point.y() < pb.y()) {
                double d = pb.x() - pa.x();
                if (d == 0) {
                    x = pa.x();
                } else {
                    double a = (pb.y() - pa.y()) / d;
                    double b = pa.y() - a * pa.x();
                    x = (point.y() - b) / a;
                }
                if (x < point.x()) {
                    counter++;
                }
            }
        }
        return (counter % 2 != 0);
    }
}
