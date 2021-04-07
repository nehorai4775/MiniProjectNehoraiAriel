package geometries;
import primitives.*;

import java.util.Objects;

public class Tube implements Geometry{
    private Ray _axisRay;
    private double _radius;
// a constructor
    public Tube(Ray axisRay,double radius){
        _axisRay = axisRay;
        if(radius==0)
            throw new IllegalArgumentException();
        _radius = radius;
    }
    //a function that returns the normal
    public Vector getNormal(Point3D p){

        double t =_axisRay.getDir().dotProduct(p.subtract(_axisRay.getP0()));
        Point3D o=_axisRay.getP0().add( _axisRay.getDir().scale(t));
return p.subtract(o).normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tube tube = (Tube) o;
        return Double.compare(tube._radius, _radius) == 0 && _axisRay.equals(tube._axisRay);
    }
//a function that returns the axisRay

    public Ray getAxisRay() {
        return _axisRay;
    }
    //a function that returns the radius
    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }
}
