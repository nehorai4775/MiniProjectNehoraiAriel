package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Cylinder extends Tube {

    double _height;


    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        if (height < 0 || isZero(height))
        {
            throw new IllegalArgumentException("Cylinder can't have non-positive height!");
        }
        _height = height;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = super.findGeoIntersections(ray);

        Point3D p0 = _axisRay.getP0();
        Vector dir = _axisRay.getDir();

        if (intersections != null) {
            List<GeoPoint> temp = new LinkedList<>();

            for (GeoPoint g : intersections) {
                double pointHeight = alignZero(g._point.subtract(p0).dotProduct(dir));
                if (pointHeight > 0 && pointHeight < _height) {
                    temp.add(g);
                }
            }

            if (temp.isEmpty()) {
                intersections = null;
            }
            else {
                intersections = temp;
            }
        }

        List<GeoPoint> planeIntersection = new Plane(p0, dir).findGeoIntersections(ray);
        if (planeIntersection != null) {
            GeoPoint point = planeIntersection.get(0);
            if (point._point.equals(p0) || alignZero(point._point.subtract(p0).lengthSquared() - _radius * _radius) < 0) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                point._geometry = this;
                intersections.add(point);
            }
        }

        Point3D p1 = p0.add(dir.scale(_height));

        planeIntersection = new Plane(p1, dir).findGeoIntersections(ray);
        if (planeIntersection != null) {
            GeoPoint point = planeIntersection.get(0);
            if (point._point.equals(p1) || alignZero(point._point.subtract(p1).lengthSquared() - _radius * _radius) < 0) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                point._geometry = this;
                intersections.add(point);
            }
        }

        if (intersections != null) {
            for (GeoPoint g : intersections) {
                g._geometry = this;
            }
        }

        return intersections;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Plane pl1 = new Plane(_axisRay.getP0(), _axisRay.getDir());

        if (pl1.inPlane(p)) {
            return _axisRay.getDir().scale(-1);
        }

        Plane pl2 = new Plane(_axisRay.getP0().add(_axisRay.getDir().scale(_height)), _axisRay.getDir());

        if (pl2.inPlane(p)) {
            return _axisRay.getDir();
        }

        return super.getNormal(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cylinder cylinder = (Cylinder) o;
        return Double.compare(cylinder._height, _height) == 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\nheight = " + _height;
    }
}
