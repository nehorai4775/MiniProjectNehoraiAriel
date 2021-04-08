package geometries;
import primitives.*;

import java.util.Objects;

public class Sphere implements Geometry{
    private Point3D _center;
    private double _radius;
// a constructor
    public Sphere(Point3D center,double radius){

        _center = center;
        if(radius==0)
            throw new IllegalArgumentException();
        _radius = radius;
    }
    //a function that returns the normal
    public Vector getNormal(Point3D p){
        //according to what that be displayed

        Vector v1=p.subtract(_center);
        return v1.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(sphere._radius, _radius) == 0 && _center.equals(sphere._center);
    }

    public Point3D getCenter() {

        return _center;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }
}
