package org.example;

import java.util.ArrayList;
import java.util.List;

public class Land extends  Polygon{

    public Land(List<Point> points) {
        super(points);
    }
    private List<City> cities = new ArrayList<>();

    void addCity(City city) throws RuntimeException{
        if(this.inside(city.center)){
            cities.add(city);
        }
        else
        {
            throw new RuntimeException(city.name);
        }
    }
}
