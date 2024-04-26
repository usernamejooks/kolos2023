package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class City extends Polygon {

    public final Point center;
    public final String name;

    private final List<Point> cityWallPoints;


    public City(Point center, String name, double wallLength) {
        super(generateCityWall(center, wallLength));
        this.center = center;
        this.name = name;
        this.cityWallPoints = generateCityWall(center, wallLength);
    }

    private static List<Point> generateCityWall(Point center, double wallLength) {
        Point point1 = new Point(center.x() + wallLength / 2, center.y() + wallLength / 2);
        Point point2 = new Point(center.x() + wallLength / 2, center.y() - wallLength / 2);
        Point point3 = new Point(center.x() - wallLength / 2, center.y() + wallLength / 2);
        Point point4 = new Point(center.x() - wallLength / 2, center.y() - wallLength / 2);
        List<Point> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        return points;
    }

    private boolean port;

    void isPort(City city, Land land) {
        for (int i = 0; i < 4; i++) {
            if (!land.inside(city.cityWallPoints.get(i))) {
                port = true;
                break;
            }
            port = false;
        }
    }
    Set<Resource.Type> resources = new HashSet<>();
    void addResourcesInRange(List<Resource> resourceList,double range){
        for(Resource resource: resourceList){
            double distance = (Math.sqrt(Math.pow(center.x()-resource.point.x(),center.y()-resource.point.y())));
            if(range >= distance){
                if(resource.type== Resource.Type.Fish){
                    if(this.port){
                        resources.add(resource.type);
                    }
                }
                resources.add(resource.type);
            }
        }
    }
}
