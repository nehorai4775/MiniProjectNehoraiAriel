package geometries;
import primitives.*;

import java.util.Objects;

public class Tube implements Geometry{
    private Ray _axisRay;
    private double _radius;

    public Tube(Ray axisRay,double radius){
        _axisRay = axisRay;
        _radius = radius;
    }
    public Vector getNormal(Point3D p){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tube tube = (Tube) o;
        return Double.compare(tube._radius, _radius) == 0 && _axisRay.equals(tube._axisRay);
    }

    public void setRadius(double radius) {
        _radius = radius;
    }

    public Ray getAxisRay() {
        return _axisRay;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }
}
