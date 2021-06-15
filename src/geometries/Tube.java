package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Tube extends Geometry {
    final double _radius;
    final Ray _axisRay;

    public Tube(double radius, Ray axisRay) {
        _radius = radius;
        _axisRay = axisRay;
    }

    public double getRadius() {
        return _radius;
    }

    public Ray getAxisRay() {
        return _axisRay;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Point3D p0 = _axisRay.getP0();
        Vector v = _axisRay.getDir();
        Vector p0_p = p.subtract(p0);

        double t = alignZero(p0_p.dotProduct(v));
        if (t == 0) {
            return p0_p.normalize();
        }

        Point3D o = p0.add(v.scale(t));

        if (o.equals(p0)) {
            throw new IllegalArgumentException("Point p can't be on ray axis.");
        }

        return p.subtract(o).normalize();
    }

    @Override
    public String toString() {
        return "radius = " + _radius +
                "\naxisRay = " + _axisRay;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        Vector d = ray.getDir();
        Vector v = _axisRay.getDir();
        double dv = d.dotProduct(v);

        if (ray.getP0().equals(_axisRay.getP0())) {
            if (isZero(dv)) {
                return List.of(new GeoPoint(this, ray.getPoint(_radius)));
            }

            Vector dvv = v.scale(d.dotProduct(v));

            if (d.equals(dvv)) {
                return null;
            }

            return List.of(new GeoPoint(this, ray.getPoint(Math.sqrt(_radius * _radius / d.subtract(dvv).lengthSquared()))));
        }

        Vector x = ray.getP0().subtract(_axisRay.getP0());

        double xv = x.dotProduct(v);

        double a = 1 - dv * dv;
        double b = 2 * d.dotProduct(x) - 2 * dv * xv;
        double c = x.lengthSquared() - xv * xv - _radius * _radius;

        if (isZero(a)) {
            if (isZero(b)) {
                return null;
            }
            return List.of(new GeoPoint(this, ray.getPoint(-c / b)));
        }

        double delta = alignZero(b * b - 4 * a * c);

        if (delta <= 0)
            return null;

        List<GeoPoint> intersections = null;
        double t = alignZero(-(b + Math.sqrt(delta)) / (2 * a));
        if (t > 0) {
            intersections = new LinkedList<>();
            intersections.add(new GeoPoint(this, ray.getPoint(t)));
        }
        t = alignZero(-(b - Math.sqrt(delta)) / (2 * a));
        if (t > 0) {
            if (intersections == null) {
                intersections = new LinkedList<>();
                intersections.add(new GeoPoint(this, ray.getPoint(t)));
            }
            else {
                intersections.add(new GeoPoint(this, ray.getPoint(t)));
            }
        }

        return intersections;
    }
}
