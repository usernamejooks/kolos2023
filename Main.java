package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.example.Resource.Type.Coal;

public class Main {
    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1,3));
        points.add(new Point(2.3,15.1));
        points.add(new Point(9,4));
        Polygon polygon = new Polygon(points);
        Point point1 = new Point(5,10);
        boolean inside;
        inside = polygon.inside(point1);
        System.out.println(inside);
        City city = new City(new Point(33,10),"Watykan",15);
        Land land = new Land(points);
        land.addCity(city);
    }
}
