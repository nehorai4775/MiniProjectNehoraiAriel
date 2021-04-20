package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


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
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> points=new ArrayList<Point3D>();
        if(isZero(ray.getP0().getX().getCoord() -_center.getX().getCoord())&&isZero(ray.getP0().getY().getCoord()-_center.getY().getCoord())
                &&isZero(ray.getP0().getZ().getCoord()-_center.getZ().getCoord()))
        {
            Point3D temp=ray.getPoint(_radius);
            points.add(temp);
            return points;
        }
        Vector u= _center.subtract(ray.getP0());
        double tm=alignZero(ray.getDir().dotProduct(u));
        double d=alignZero(Math.sqrt(u.lengthSquared()-tm*tm));
        if(d>=_radius)
            return null;
        double th= alignZero(Math.sqrt((_radius*_radius-d*d)));
        double t1= tm+th;
        double t2 =tm-th;
if(t1>0)
{
    Point3D temp=ray.getPoint(t1);
    points.add(temp);
    }
        if(t2>0)
        {
            Point3D temp=ray.getPoint(t2);
            points.add(temp);
        }
        if(isZero(points.size()))
            return null;
        return points;
}



}
