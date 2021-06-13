package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * a class for sphere
 */
public class Sphere extends Geometry {
    private Point3D _center;
    private double _radius;

    /**
     * constructor
     *
     * @param radius-radius
     * @param center-center point
     */
    public Sphere(double radius, Point3D center) {

        _center = center;
        if (radius == 0)
            throw new IllegalArgumentException();
        _radius = radius;
    }

    /**
     * getter
     *
     * @param p-point
     * @return vector
     */
    public Vector getNormal(Point3D p) {
        //according to what that be displayed

        Vector v1 = p.subtract(_center);
        return v1.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(sphere._radius, _radius) == 0 && _center.equals(sphere._center);
    }

    /**
     * getter
     *
     * @return center
     */
    public Point3D getCenter() {

        return _center;
    }

    /**
     * getter
     *
     * @return radius
     */
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


    /**
     * @param ray-ray
     * @return Intersections points
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {

        Point3D p0 = ray.getP0();
        Vector v = ray.getDir();
        Point3D o = this._center;
        double radius = _radius;
//in case that the head of the ray is the center of the sphere we add to the vector the radius
        if (p0.equals(o))
            return List.of(new GeoPoint(this, ray.getPoint(radius)));

        Vector U = o.subtract(p0);
        double tm = v.dotProduct(U);
        double d = Math.sqrt(U.lengthSquared() - tm * tm);

        if (alignZero(d - radius) >= 0)
            return null;


        double th = Math.sqrt(radius * radius - d * d);
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
            Point3D P1 = ray.getPoint(t1);
            Point3D P2 = ray.getPoint(t2);


            return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));


        }

        if (t1 > 0) {
            Point3D P1 = ray.getPoint(t1);

            return List.of(new GeoPoint(this, P1));
        }

        if (t2 > 0) {
            Point3D P2 = ray.getPoint(t2);

            return List.of(new GeoPoint(this, P2));
        }
        return null;

    }
}
